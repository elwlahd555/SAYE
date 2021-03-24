<template>
  <section id="intro" class="full-screen pt-5 black">
    <Row
      title="최신 음악"
      subTitle="가장 최근에 발매된 뮤직비디오 목록입니다."
      :musicList="latestMusic"
    />
  </section>
</template>

<script>
import axios from "axios";

import Row from "@/components/home/Slide0/Row";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  components: {
    Row
  },
  data: function() {
    return {
      latestMusic: []
    };
  },
  computed: {
    exposedLatestMusic() {
      return this.latestMusic.slice(0, 6);
    }
  },
  mounted() {
    this.getLatestMusic();
  },
  methods: {
    getLatestMusic: function() {
      axios
        .get(`${spring_URL}` + "/music/popularity")
        .then(response => {
          this.latestMusic = response.data.map(music => {
            music.isActive = false;
            music.isArrow = false;
            music.isFavorite = music.isFavorite === 1;
            return music;
          });
        })
        .catch(error => {
          console.log(error);
        });
    },
    playLatestMusic: function(videoId) {
      const playlist = this.exposedLatestMusic.map(music => {
        return {
          video: music.video,
          artist: music.artistId,
          title: music.title,
          singer: music.singer
        };
      });

      this.$store.dispatch("setPlaylist", playlist);
      this.$store.dispatch("setVideoId", videoId);
    }
  }
};
</script>

<style scoped lang="scss">
#intro {
  & {
    width: 100%;
    color: white;
  }
  .container {
    width: 100%;
    padding: 30px 50px 20px;
    margin: 0 auto;
  }

  #latest_music_title {
    font-size: 26px;
    margin: 0 0 8px 0;
    display: block;
    padding-left: 10px;
  }
  #latest_music_subtitle {
    font-size: 18px;
    margin: 0 0 12px 0;
    display: inline-block;
    vertical-align: top;
    padding-left: 10px;
  }
  #latest_music_more_a {
    & {
      display: inline-block;
      color: white;
      float: right;
      margin-top: 6px;
      padding-right: 10px;
      font-weight: 400;
      text-decoration: none;
      cursor: pointer;
      line-height: 1;
    }
    span {
      font-size: 18px;
      vertical-align: top;
    }
    /*#latest_music_more_span { font-size: 16pt; margin-top: 0; }*/
    &:hover #latest_music_more_span {
      text-decoration: underline;
    }
  }
}
</style>
