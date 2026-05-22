# Validates new Sanity/Mux credentials and writes BeOn/.env
# Run AFTER creating new tokens in each provider dashboard.

$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $PSScriptRoot
$envFile = Join-Path $root ".env"

Write-Host ""
Write-Host "BeOn — setup de credenciales rotadas" -ForegroundColor Cyan
Write-Host "Crea los tokens nuevos en los dashboards antes de continuar." -ForegroundColor DarkGray
Write-Host ""

$sanityProjectId = Read-Host "SANITY_PROJECT_ID"
$sanityDataset = Read-Host "SANITY_DATASET [production]"
if ([string]::IsNullOrWhiteSpace($sanityDataset)) { $sanityDataset = "production" }
$sanityToken = Read-Host "SANITY_API_TOKEN (nuevo)" -AsSecureString
$sanityTokenPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto(
    [Runtime.InteropServices.Marshal]::SecureStringToBSTR($sanityToken)
)

$muxTokenId = Read-Host "MUX_TOKEN_ID (nuevo)"
$muxTokenSecret = Read-Host "MUX_TOKEN_SECRET (nuevo)" -AsSecureString
$muxTokenSecretPlain = [Runtime.InteropServices.Marshal]::PtrToStringAuto(
    [Runtime.InteropServices.Marshal]::SecureStringToBSTR($muxTokenSecret)
)

Write-Host ""
Write-Host "Validando Sanity..." -ForegroundColor Yellow
$sanityHeaders = @{ Authorization = "Bearer $sanityTokenPlain" }
$sanityQuery = [uri]::EscapeDataString("*[0]._id")
$sanityUrl = "https://$sanityProjectId.api.sanity.io/v2024-01-01/data/query/$sanityDataset`?query=$sanityQuery"
try {
    $sanityResult = Invoke-RestMethod -Uri $sanityUrl -Headers $sanityHeaders -Method Get
    if ($null -eq $sanityResult.result) { throw "Respuesta inesperada de Sanity." }
    Write-Host "  OK — Sanity responde correctamente." -ForegroundColor Green
} catch {
    Write-Host "  ERROR — Sanity rechazó el token: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

Write-Host "Validando Mux..." -ForegroundColor Yellow
$muxPair = "$muxTokenId`:$muxTokenSecretPlain"
$muxB64 = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes($muxPair))
try {
    $muxResult = Invoke-RestMethod -Uri "https://api.mux.com/video/v1/assets?limit=1" -Headers @{ Authorization = "Basic $muxB64" } -Method Get
    if ($null -eq $muxResult.data) { throw "Respuesta inesperada de Mux." }
    Write-Host "  OK — Mux responde correctamente." -ForegroundColor Green
} catch {
    Write-Host "  ERROR — Mux rechazó el token: $($_.Exception.Message)" -ForegroundColor Red
    exit 1
}

$content = @"
# Rotated credentials — generated $(Get-Date -Format "yyyy-MM-dd HH:mm")
SANITY_PROJECT_ID=$sanityProjectId
SANITY_DATASET=$sanityDataset
SANITY_API_TOKEN=$sanityTokenPlain

MUX_TOKEN_ID=$muxTokenId
MUX_TOKEN_SECRET=$muxTokenSecretPlain
"@

Set-Content -Path $envFile -Value $content -Encoding UTF8 -NoNewline
Write-Host ""
Write-Host "Guardado en: $envFile" -ForegroundColor Green
Write-Host ""
Write-Host "IMPORTANTE: revoca los tokens viejos en:" -ForegroundColor Yellow
Write-Host "  Sanity: https://www.sanity.io/manage/project/$sanityProjectId/api" -ForegroundColor DarkGray
Write-Host "  Mux:    https://dashboard.mux.com/settings/access-tokens" -ForegroundColor DarkGray
Write-Host ""
