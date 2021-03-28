<template>
  <section id="intro" class="full-screen pt-5 black">
    <Row
      title="최신 음악"
      subTitle="가장 최근에 발매된 음악 목록입니다."
      :musicList="latestMusic"
    />
    <Row
      title="뜨는 음악"
      subTitle="가장 인기 있는 음악 목록입니다."
      :musicList="popularMusic"
    />
  </section>
</template>

<script>
import axios from "axios";

import Row from "@/components/home/Slide0/Row";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  components: {
    Row,
  },
  data: function () {
    return {
      latestMusic: [],
      popularMusic: [],
    };
  },
  computed: {
    exposedLatestMusic() {
      return this.latestMusic.slice(0, 6);
    },
  },
  mounted() {
    this.getLatestMusic();
    this.getPopularMusic();
  },
  methods: {
    getLatestMusic: function () {
      axios
        .get(`${spring_URL}` + "/music/date")
        .then((response) => {
          this.latestMusic = response.data.map((music) => {
            music.isActive = false;
            music.isArrow = false;
            music.isFavorite = music.isFavorite === 1;
            return music;
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getPopularMusic: function () {
      axios
        .get(`${spring_URL}` + "/music/popularity")
        .then((response) => {
          this.popularMusic = response.data.map((music) => {
            music.isActive = false;
            music.isArrow = false;
            music.isFavorite = music.isFavorite === 1;
            return music;
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },
    playLatestMusic: function (videoId) {
      const playlist = this.exposedLatestMusic.map((music) => {
        return {
          video: music.video,
          artist: music.artistId,
          title: music.title,
          singer: music.singer,
        };
      });

      this.$store.dispatch("setPlaylist", playlist);
      this.$store.dispatch("setVideoId", videoId);
    },
  },
};
</script>

<style scoped lang="scss">
#intro {
  & {
    width: 100%;
    color: white;
  }
}
</style>
