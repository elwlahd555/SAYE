<template>
  <div>
    <aside id="frame_aside_mini" v-show="!playerShow">
      <v-icon
        class="mt-5 ml-1"
        large
        color="red"
        @click="playerShow = !playerShow"
      >
        mdi-chevron-left-box
      </v-icon>

      <div style="margin-top: 7vh" class="ml-1">
        <v-icon large @click="prev">mdi-skip-previous-circle</v-icon>
        <v-icon large v-if="playerStatus === 'ENDED'" @click="play"
          >mdi-stop-circle</v-icon
        >
        <v-icon large v-if="playerStatus === 'PAUSED'" @click="play"
          >mdi-play-circle</v-icon
        >
        <v-icon large v-if="playerStatus === 'PLAYING'" @click="pause"
          >mdi-pause-circle</v-icon
        >
        <v-icon large @click="next">mdi-skip-next-circle</v-icon>
        <div
          style="
            transform: rotateZ(90deg);
            transform-origin: left bottom;
            height: 30px;
            width: 200px;
          "
        >
          <p class="text-center subtitle-1 font-weight-bold text-truncate">
            {{ playMusic ? playMusic.mTitle : "재생중인 노래가 없습니다." }}
          </p>
        </div>
      </div>
    </aside>

    <aside id="frame_aside" v-show="playerShow">
      <v-icon
        large
        color="red"
        class="mt-5 ml-2"
        @click="playerShow = !playerShow"
      >
        mdi-chevron-right-box
      </v-icon>
      <div
        id="player_container"
        class="noselect"
        ref="playerContainer"
        @mouseleave="playerLeave"
        @mouseup="playerLeave"
      >
        <div id="player">
          <span>뮤직 플레이어</span>
        </div>

        <div id="player_control_wrap" class="on">
          <div>
            <p id="player_control_music_title">{{ musicTitle }}</p>
            <p id="player_control_music_time">{{ musicTime }}</p>
            <div
              id="player_control_progress_bar"
              ref="playerProgressBar"
              @mousedown="progressBarDown"
              @mouseup="progressBarUp"
              @mouseenter="progressBarEnter"
              @mouseleave="progressBarLeave"
              @mousemove="progressBarMove"
            >
              <div
                id="player_control_progress_bar_active"
                :style="{ width: playerProgressBarWidth + '%' }"
              ></div>
            </div>
            <v-icon
              id="player_control_play_btn"
              v-if="playerStatus === 'PLAYING'"
              @click="pause"
            >
              mdi-pause
            </v-icon>
            <v-icon id="player_control_play_btn" v-else @click="play">
              mdi-play
            </v-icon>
            <v-icon id="player_control_prev_btn" @click="prev">
              mdi-skip-backward
            </v-icon>
            <v-icon id="player_control_next_btn" @click="next">
              mdi-skip-forward
            </v-icon>
            <v-icon id="player_control_large_btn" @click="fullscreen">
              mdi-arrow-expand
            </v-icon>
            <v-icon
              id="player_control_repeat_btn"
              v-if="playerRepeat"
              @click="repeat(false)"
            >
              mdi-repeat-off
            </v-icon>
            <v-icon id="player_control_repeat_btn" v-else @click="repeat(true)">
              mdi-repeat
            </v-icon>
            <v-icon
              id="player_control_shuffle_btn"
              v-if="playerShuffle"
              @click="repeat(false)"
            >
              mdi-shuffle-disabled
            </v-icon>
            <v-icon
              id="player_control_shuffle_btn"
              v-else
              @click="repeat(true)"
            >
              mdi-shuffle-variant
            </v-icon>
            <v-icon
              id="player_control_volume_btn"
              v-if="playerMute"
              @click="mute(false)"
            >
              mdi-volume-off
            </v-icon>
            <v-icon id="player_control_volume_btn" v-else @click="mute(true)">
              mdi-volume-high
            </v-icon>
            <div
              id="player_control_volume_bar"
              ref="playerVolumeBar"
              @mousedown="volumeBarDown"
              @mouseup="volumeBarUp"
              @mouseenter="volumeBarEnter"
              @mouseleave="volumeBarLeave"
              @mousemove="volumeBarMove"
            >
              <div
                id="player_control_volume_bar_active"
                :style="{ width: playerVolumeBarsWidth + '%' }"
              ></div>
            </div>
          </div>
        </div>
      </div>

      <div id="aside_container">
        <div id="aside_partition">
          <div id="aside_partition_text_wrap">
            <span id="active_lyrics_wrap_button" class="aside_partition_text"
              >재생목록</span
            >
          </div>
        </div>
        <div id="aside_contents_wrap">
          <div id="aside_lyrics_wrap" v-if="videoId === null">
            <div id="aside_lyrics_container" ref="lyricsContainer">
              <p class="aside_lyrics_title empty">재생중인 음악이 없습니다</p>
            </div>
          </div>
          <Playlist v-else :playlist="asidePlaylist" />
        </div>
      </div>
    </aside>
  </div>
</template>

<script>
import getYouTubeID from "get-youtube-id";
import { mapState } from "vuex";

import Playlist from "@/components/mypage/Playlist";

export default {
  name: "Header",
  components: {
    Playlist
  },
  data: function() {
    return {
      YT: null,
      player: null,
      playerShow: false,
      playerStatus: "ENDED",
      playerInterval: null,
      playerProgressBarWidth: "0",
      playerVolumeBarsWidth: "100",
      playerRepeat: false,
      playerShuffle: false,
      playerMute: false,
      playCount: 0,
      isProgressBarDown: false,
      isProgressBarHover: false,
      isVolumeBarDown: false,
      isVolumeBarHover: false,
      musicTitle: "",
      musicTime: "0:00 / 0:00"
    };
  },
  computed: {
    ...mapState({
      playMusic: "playMusic",
      asidePlaylist: "asidePlaylist",
      videoId: "videoId"
    }),
    playlist() {
      let playlist = this.$store.getters.playlist.map(item => item);

      if (this.playerShuffle) {
        let j, x, i;
        for (i = playlist.length; i; i -= 1) {
          j = Math.floor(Math.random() * i);
          x = playlist[i - 1];
          playlist[i - 1] = playlist[j];
          playlist[j] = x;
        }
      }

      return playlist;
    }
  },
  watch: {
    videoId(videoId) {
      this.start(videoId);
    }
  },
  mounted() {
    // Youtube Player
    let tag = document.createElement("script");
    tag.src = "https://www.youtube.com/iframe_api";
    let firstScriptTag = document.getElementsByTagName("script")[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    window.onYouTubeIframeAPIReady = this.onYouTubeIframeAPIReady;
  },
  methods: {
    onYouTubeIframeAPIReady: function() {
      this.YT = window.YT;
    },
    onPlayerReady: function(event) {
      event.target.playVideo();
    },
    onPlayerStateChange: function(event) {
      if (event.data === this.YT.PlayerState.ENDED) {
        this.playerStatus = "ENDED";

        if (this.playerInterval != null) {
          clearInterval(this.playerInterval);
          this.playerInterval = null;
        }

        if (this.playerRepeat) {
          this.playCount = 0;
          this.player.seekTo(0);
        } else {
          this.next();
        }
      } else if (event.data === this.YT.PlayerState.PLAYING) {
        this.playerStatus = "PLAYING";

        if (this.playerInterval == null) {
          this.playerInterval = setInterval(() => {
            const curTime = this.player.getCurrentTime();
            const duration = this.player.getDuration();

            if (curTime === undefined || duration === undefined) {
              return;
            }

            this.playerProgressBarWidth = (curTime / duration) * 100;
            this.musicTime =
              parseInt(curTime / 60) +
              ":" +
              (parseInt(curTime) % 60 < 10
                ? "0" + (parseInt(curTime) % 60)
                : parseInt(curTime) % 60) +
              " / " +
              parseInt(duration / 60) +
              ":" +
              (parseInt(duration) % 60 < 10
                ? "0" + (parseInt(duration) % 60)
                : parseInt(duration) % 60);

            this.playCount++;
            if (this.playCount === 300) {
              const video = this.playlist.filter(
                item => item.video === this.videoId
              )[0];

              this.axios
                .post(this.$store.getters.serverUrl + "api/music/play", {
                  video: video.video,
                  artist: video.artist
                })
                .then(() => {
                  //
                })
                .catch(error => {
                  console.log(error);
                });
            }
          }, 200);
        }
      } else if (
        event.data === this.YT.PlayerState.PAUSED ||
        event.data === this.YT.PlayerState.UNSTARTED ||
        event.data === this.YT.PlayerState.BUFFERING
      ) {
        this.playerStatus = "PAUSED";

        if (this.playerInterval != null) {
          clearInterval(this.playerInterval);
          this.playerInterval = null;
        }
      }
    },
    start: function(videoId) {
      this.playCount = 0;

      if (this.player === null) {
        this.player = new this.YT.Player("player", {
          playerVars: {
            autoplay: 1,
            controls: 0,
            cc_load_policy: 0,
            disablekb: 1,
            iv_load_policy: 3,
            loop: 1,
            modestbranding: 1,
            rel: 0,
            showinfo: 0,
            playsinline: 0
          },
          videoId: videoId,
          events: {
            onReady: this.onPlayerReady,
            onStateChange: this.onPlayerStateChange
          }
        });
      } else {
        try {
          this.player.loadVideoById(videoId, 0, "hd1080");
        } catch (e) {
          console.log(
            "아직 음악을 재생할 준비가 되지 않았습니다<br/>다시 한번 시도해주세요"
          );
        }
      }

      //const video = this.playlist.filter((item) => item.video === videoId)[0];
      //this.musicTitle = video.title + " - " + video.singer[0].name;
    },
    play: function() {
      if (this.player === null) {
        return;
      }
      this.player.playVideo();
    },
    pause: function() {
      if (this.player === null) {
        return;
      }
      this.player.playVideo();
      this.player.pauseVideo();
    },
    prev: function() {
      if (this.player === null) {
        return;
      }

      // if (this.playerInterval != null) {
      //   clearInterval(this.playerInterval);
      //   this.playerInterval = null;
      // }

      const currentVideoIndex = this.asidePlaylist
        .map(item => item.mId)
        .indexOf(this.playMusic.mId);

      let nxtId = null;
      let nxtMusic = null;
      if (currentVideoIndex === 0) {
        nxtId = getYouTubeID(
          this.asidePlaylist[this.asidePlaylist.length - 1].mUrl
        );
        nxtMusic = this.asidePlaylist[this.asidePlaylist.length - 1];
      } else {
        nxtId = getYouTubeID(this.asidePlaylist[currentVideoIndex - 1].mUrl);
        nxtMusic = this.asidePlaylist[currentVideoIndex - 1];
      }
      this.$store.dispatch("setVideoId", nxtId);
      this.$store.dispatch("setPlayMusic", nxtMusic);
    },
    next: function() {
      if (this.player === null) {
        return;
      }

      // if (this.playerInterval != null) {
      //   clearInterval(this.playerInterval);
      //   this.playerInterval = null;
      // }

      const currentVideoIndex = this.asidePlaylist
        .map(item => item.mId)
        .indexOf(this.playMusic.mId);

      let nxtId = null;
      let nxtMusic = null;
      if (currentVideoIndex === this.asidePlaylist.length - 1) {
        nxtId = getYouTubeID(this.asidePlaylist[0].mUrl);
        nxtMusic = this.asidePlaylist[0];
      } else {
        nxtId = getYouTubeID(this.asidePlaylist[currentVideoIndex + 1].mUrl);
        nxtMusic = this.asidePlaylist[currentVideoIndex + 1];
      }
      this.$store.dispatch("setVideoId", nxtId);
      this.$store.dispatch("setPlayMusic", nxtMusic);
    },
    repeat: function(playerRepeat) {
      if (this.player === null) {
        return;
      }
      alert("죄송해여 아직 구현 중입니다ㅠ");
      this.playerRepeat = playerRepeat;
    },
    shuffle: function(playerShuffle) {
      if (this.player === null) {
        return;
      }
      alert("죄송해여 아직 구현 중입니다ㅠ");
      this.playerShuffle = playerShuffle;
    },
    mute: function(playerMute) {
      if (this.player === null) {
        return;
      }

      this.playerMute = playerMute;

      if (playerMute) {
        this.player.setVolume(0);
        this.playerVolumeBarsWidth = 0;
      } else {
        this.player.setVolume(80);
        this.playerVolumeBarsWidth = 80;
      }
    },
    fullscreen: function() {
      if (this.player === null) {
        return;
      }

      var ppp = document.querySelector.bind(document);
      var requestFullScreen =
        ppp("#player").requestFullScreen ||
        ppp("#player").mozRequestFullScreen ||
        ppp("#player").webkitRequestFullScreen;
      if (requestFullScreen) {
        requestFullScreen.bind(ppp("#player"))();
      }
    },
    progressBarDown: function(event) {
      if (this.player === null) {
        return;
      }

      this.isProgressBarDown = true;
      this.isProgressBarHover = true;

      this.progressBarHandle(event);
    },
    progressBarUp: function() {
      this.isProgressBarHover = false;
    },
    progressBarEnter: function() {
      if (this.isProgressBarDown) {
        this.isProgressBarHover = true;
      }
    },
    progressBarLeave: function() {
      this.isProgressBarHover = false;
    },
    progressBarMove: function(event) {
      if (this.isProgressBarDown) {
        this.progressBarHandle(event);
      }
    },
    progressBarHandle: function(event) {
      if (this.isProgressBarHover) {
        let x;
        if (event.target.id === "player_control_progress_bar")
          x =
            event.pageX -
            (event.target.parentNode.parentNode.parentNode.parentNode
              .offsetLeft +
              event.target.offsetLeft);
        else
          x =
            event.pageX -
            (event.target.parentNode.parentNode.parentNode.parentNode.parentNode
              .offsetLeft +
              event.target.parentNode.offsetLeft);

        const curTime = this.player.getCurrentTime();
        const duration = this.player.getDuration();

        this.player.seekTo(
          (duration * x) / this.$refs.playerProgressBar.clientWidth
        );
        this.playerProgressBarWidth = (curTime / duration) * 100;
        this.musicTime =
          parseInt(curTime / 60) +
          ":" +
          (parseInt(curTime) % 60 < 10
            ? "0" + (parseInt(curTime) % 60)
            : parseInt(curTime) % 60) +
          " / " +
          parseInt(duration / 60) +
          ":" +
          (parseInt(duration) % 60 < 10
            ? "0" + (parseInt(duration) % 60)
            : parseInt(duration) % 60);
      }
    },
    volumeBarDown: function(event) {
      if (this.player === null) {
        return;
      }

      this.isVolumeBarDown = true;
      this.isVolumeBarHover = true;

      this.volumeBarHandle(event);
    },
    volumeBarUp: function() {
      this.isVolumeBarHover = false;
    },
    volumeBarEnter: function() {
      if (this.isVolumeBarDown) {
        this.isVolumeBarHover = true;
      }
    },
    volumeBarLeave: function(event) {
      if (this.isVolumeBarHover) {
        if (event.offsetX >= 100) {
          this.playerVolumeBarsWidth = "100";
          this.player.setVolume(100);
          this.playerMute = false;
        } else if (event.offsetX <= 0) {
          this.playerVolumeBarsWidth = "0";
          this.player.setVolume(0);
          this.playerMute = true;
        }
      }
      this.isVolumeBarHover = false;
    },
    volumeBarMove: function(event) {
      if (this.isVolumeBarDown) {
        this.volumeBarHandle(event);
      }
    },
    volumeBarHandle: function(event) {
      if (this.isVolumeBarHover) {
        let x;
        if (event.target.id === "player_control_volume_bar")
          x =
            event.pageX -
            (event.target.parentNode.parentNode.parentNode.parentNode
              .offsetLeft +
              event.target.offsetLeft);
        else
          x =
            event.pageX -
            (event.target.parentNode.parentNode.parentNode.parentNode.parentNode
              .offsetLeft +
              event.target.parentNode.offsetLeft);
        const volume = parseInt(
          (x / this.$refs.playerVolumeBar.clientWidth) * 100
        );
        this.playerVolumeBarsWidth = volume;
        this.player.setVolume(volume);
        if (volume <= 0) {
          this.playerMute = true;
        } else {
          this.playerMute = false;
        }
      }
    },
    playerLeave: function() {
      this.isProgressBarDown = false;
      this.isProgressBarHover = false;
      this.isVolumeBarDown = false;
      this.isVolumeBarHover = false;
    }
  }
};
</script>

<style lang="scss">
#frame_aside #player_container #player {
  width: 100%;
  height: 253.125px;
  background-color: #000;
  display: block;
  line-height: 253.125px;
  text-align: center;
  color: #ffffff;
  font-weight: 400;
}

#frame_aside #aside_lyrics_wrap {
  & {
    width: 100%;
    height: 100%;
    display: none;
    padding-bottom: 10px;
  }
  &.on {
    display: block;
  }

  #aside_lyrics_container {
    position: relative;
    width: 100%;
    height: 100%;
    overflow-y: auto;
  }
  .aside_lyrics_title {
    & {
      margin: 0 0 19px 0;
      padding: 0;
      font-size: 15px;
      font-weight: 500;
      line-height: 23px;
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
      color: #333333;
    }
    &.empty {
      text-align: center;
      margin-top: 30px;
    }
  }
  .aside_lyrics_contents {
    margin: 0;
    font-size: 15px;
    font-weight: 400;
    line-height: 27px;
    color: #666666;
  }
  .aside_lyrics_nbsp_contents {
    margin: 0;
    font-size: 15px;
    line-height: 1;
  }
}
</style>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
#frame_aside_mini {
  position: fixed;
  width: 70px;
  height: 100%;
  min-height: 500px;
  right: 0;
  top: 0;
  padding: 10px;
  border-left: 1px solid #aaaaaa;
  background-color: #ffffff;
  z-index: 999;
}
#frame_aside {
  & {
    position: fixed;
    width: 450px;
    height: 100%;
    min-height: 500px;
    right: 0;
    top: 0;
    border-left: 1px solid #aaaaaa;
    background-color: #ffffff;
    z-index: 999;
  }

  #player_container {
    & {
      position: relative;
      padding-top: 48px;
      width: 450px;
    }

    #player {
      width: 100%;
      height: 253.125px;
      background-color: #000;
      display: block;
      line-height: 253.125px;
      text-align: center;
      color: #ffffff;
      font-weight: 400;
    }
    #player > span {
      display: block;
      padding-top: 30px;
      font-size: 18px;
    }

    #player_control_wrap {
      & {
        position: relative;
        width: 100%;
        height: 120px;
        display: block;
        z-index: 90;
        opacity: 1;
        background: rgba(0, 0, 0, 0.75);
        background: -webkit-linear-gradient(
          rgba(0, 0, 0, 1),
          rgba(0, 0, 0, 0.9),
          rgba(0, 0, 0, 0.75),
          rgba(0, 0, 0, 0.6),
          rgba(0, 0, 0, 0.45),
          rgba(0, 0, 0, 0.3),
          rgba(0, 0, 0, 0)
        ); /* For Safari 5.1 to 6.0 */
        background: -o-linear-gradient(
          rgba(0, 0, 0, 1),
          rgba(0, 0, 0, 0.9),
          rgba(0, 0, 0, 0.75),
          rgba(0, 0, 0, 0.6),
          rgba(0, 0, 0, 0.45),
          rgba(0, 0, 0, 0.3),
          rgba(0, 0, 0, 0)
        ); /* For Opera 11.1 to 12.0 */
        background: -moz-linear-gradient(
          rgba(0, 0, 0, 1),
          rgba(0, 0, 0, 0.9),
          rgba(0, 0, 0, 0.75),
          rgba(0, 0, 0, 0.6),
          rgba(0, 0, 0, 0.45),
          rgba(0, 0, 0, 0.3),
          rgba(0, 0, 0, 0)
        ); /* For Firefox 3.6 to 15 */
        background: linear-gradient(
          rgba(0, 0, 0, 1),
          rgba(0, 0, 0, 0.9),
          rgba(0, 0, 0, 0.75),
          rgba(0, 0, 0, 0.6),
          rgba(0, 0, 0, 0.45),
          rgba(0, 0, 0, 0.3),
          rgba(0, 0, 0, 0)
        ); /* Standard syntax */
      }
      & > div {
        opacity: 0;
        height: 0;
        overflow: hidden;
        -moz-transition: opacity 0.4s ease;
        -webkit-transition: opacity 0.4s ease;
        -ms-transition: opacity 0.4s ease;
        transition: opacity 0.4s ease;
      }
      &.on > div {
        opacity: 1;
        height: auto;
      }
      &.large {
        position: fixed;
        bottom: 0;
        width: 100%;
        height: 120px;
        z-index: 110;
      }

      #player_control_music_title {
        width: 100%;
        height: 36px;
        margin: 0;
        padding: 10px 5% 4px;
        color: #ffffff;
        font-size: 15px;
        font-weight: 400;
        line-height: 1;
        display: block;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
      }
      #player_control_music_time {
        margin: 0;
        padding: 0 5% 9px 0;
        text-align: right;
        color: #ffffff;
        font-size: 14px;
        font-weight: 400;
        line-height: 1;
        letter-spacing: 1px;
      }
      #player_control_progress_bar {
        width: 90%;
        height: 8px;
        margin: 0 auto;
        background-color: #ffffff;
        border-radius: 4px;
        cursor: pointer;
      }
      #player_control_progress_bar_active {
        width: 0%;
        height: 8px;
        background-color: rebeccapurple;
        border-radius: 4px;
        cursor: pointer;
      }
      #player_control_play_btn {
        margin-left: 5%;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_prev_btn {
        margin-left: 14px;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_next_btn {
        margin-left: 14px;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_large_btn {
        float: right;
        margin-right: 5%;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_repeat_btn {
        margin-left: 36px;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_shuffle_btn {
        margin-left: 14px;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
      #player_control_volume_bar {
        width: 100px;
        height: 8px;
        margin: 0 auto;
        background-color: #ffffff;
        border-radius: 4px;
        cursor: pointer;
        display: inline-block;
        vertical-align: top;
        margin-top: 19px;
      }
      #player_control_volume_bar_active {
        width: 100%;
        height: 8px;
        background-color: rebeccapurple;
        border-radius: 4px;
        cursor: pointer;
      }
      #player_control_volume_btn {
        margin-right: 14px;
        margin-left: 36px;
        margin-top: 12px;
        height: 23px;
        cursor: pointer;
      }
    }
  }

  #aside_container {
    & {
      position: relative;
      width: 410px;
      height: calc(100% - 373.125px);
      margin: 0 auto;
      background-color: rgba(0, 0, 0, 0);
    }

    #aside_partition {
      & {
        width: 100%;
        display: inline-block;
        text-align: center;
        margin: 14px 0;
        font-size: 15px;
        color: #888888;
        font-weight: 400;
        position: relative;
        height: 23px;
      }
      &::after {
        content: "";
        width: 100%;
        height: 1px;
        position: absolute;
        top: 50%;
        left: 0;
        margin-top: -0.5px;
        background-color: #aaaaaa;
      }

      #aside_partition_text_wrap {
        position: relative;
        padding: 0 13px;
        box-sizing: border-box;
        background-color: #fff;
        display: inline-block;
        z-index: 1;
      }
      .aside_partition_text {
        font-weight: 500;
        color: #333333;
      }
      /*.aside_partition_text { border-bottom: 1px solid #FFFFFF; cursor: pointer; }*/
      /*.aside_partition_text:hover { border-bottom: 1px solid #888888; font-weight: 500; }*/
      /*.aside_partition_text.on { border-bottom: 1px solid #333333; font-weight: 500; color: #333333; }*/
    }

    #aside_contents_wrap {
      & {
        position: relative;
        width: 410px;
        height: calc(100% - 118px);
        margin: 0 auto;
      }
    }
  }
}

@media only screen and (max-width: 1440px) {
  #frame_aside {
    & {
      width: 410px;
    }

    #player_container {
      & {
        width: 410px;
      }

      #player_control_wrap {
        #player_control_play_btn {
          margin-top: 12px;
          height: 21px;
        }
        #player_control_prev_btn {
          margin-left: 12px;
          margin-top: 12px;
          height: 21px;
        }
        #player_control_next_btn {
          margin-left: 12px;
          margin-top: 12px;
          height: 21px;
        }
        #player_control_large_btn {
          margin-right: 5%;
          margin-top: 12px;
          height: 21px;
        }
        #player_control_repeat_btn {
          margin-left: 26px;
          margin-top: 12px;
          height: 21px;
        }
        #player_control_shuffle_btn {
          margin-left: 12px;
          margin-top: 12px;
          height: 21px;
        }
        #player_control_volume_btn {
          margin-right: 12px;
          margin-left: 26px;
          margin-top: 12px;
          height: 21px;
        }
      }
    }

    #aside_container {
      & {
        width: 370px;
      }

      #aside_contents_wrap {
        & {
          width: 370px;
        }
      }
    }
  }
}
</style>
