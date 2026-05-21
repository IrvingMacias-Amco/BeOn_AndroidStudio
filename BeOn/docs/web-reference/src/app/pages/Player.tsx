import { useNavigate, useParams } from "react-router";
import { VideoPlayer } from "../components/streaming/VideoPlayer";
import { BufferingState, DRMErrorState, PreRollAdState } from "../components/streaming/PlayerStates";
import { useState, useEffect } from "react";

type PlayerState = "ad" | "buffering" | "playing" | "error";

export function Player() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [playerState, setPlayerState] = useState<PlayerState>("ad");

  // Mock content data - in production this would come from an API based on ID
  const content = {
    title: "Friends",
    backgroundImage: "https://images.unsplash.com/photo-1627873649417-c67f701f1949?w=1600"
  };

  // Simulate ad completion
  useEffect(() => {
    if (playerState === "ad") {
      // Ad will auto-advance after its duration
    }
  }, [playerState]);

  const handleAdComplete = () => {
    setPlayerState("buffering");
    // Simulate loading
    setTimeout(() => {
      setPlayerState("playing");
    }, 1500);
  };

  const handleRetry = () => {
    setPlayerState("buffering");
    setTimeout(() => {
      setPlayerState("playing");
    }, 1500);
  };

  const handleClose = () => {
    navigate(-1);
  };

  return (
    <div className="relative w-full h-screen bg-black">
      {/* Base Video Player */}
      <VideoPlayer
        title={content.title}
        backgroundImage={content.backgroundImage}
        onClose={handleClose}
      />

      {/* Player States Overlays */}
      {playerState === "buffering" && <BufferingState />}

      {playerState === "error" && (
        <DRMErrorState onRetry={handleRetry} onClose={handleClose} />
      )}

      {playerState === "ad" && (
        <PreRollAdState
          duration={15}
          canSkip={true}
          skipAfter={5}
          onSkip={handleAdComplete}
        />
      )}
    </div>
  );
}
