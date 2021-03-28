<template>
  <v-container fluid ml-10>
    <p id="latest_music_title">{{ title }}</p>
    <p id="latest_music_subtitle">
      {{ subTitle }}
    </p>
    <!-- 
    <router-link to="/latest_music" id="latest_music_more_a">
      <span id="latest_music_more_span">더보기</span>
    </router-link>
 -->

    <v-slide-group
      style="width: 90%"
      v-model="model"
      active-class="success"
      show-arrows
      dark
    >
      <v-slide-item
        v-for="(music, idx) in musicList"
        :key="idx"
        :music="music"
        @click="playLatestMusic"
      >
        <v-hover v-slot="{ hover }">
          <v-card class="mr-3 rounded-0" width="200">
            <v-img aspect-ratio="1" :src="music.mImg" height="180px">
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex flex-row justify-center transition-fast-in-fast-out red darken-2 v-card--reveal display-1"
                  style="height: 100%"
                >
                  <v-icon x-large>mdi-youtube</v-icon>
                </div>
              </v-expand-transition>
            </v-img>

            <v-card-text class="text-center">
              {{ music.mTitle }}
            </v-card-text>
          </v-card>
        </v-hover>

        <!-- <v-hover v-slot:default="{ hover }">
          <v-card
            :elevation="hover ? 12 : 0"
            class="mr-3 rounded-0"
            :class="{ 'on-hover': hover }"
            width="200"
            @click="toggle"
          >
            <v-img
              :src="music.mImg"
              @click="handleClick(music)"
              aspect-ratio="1"
              class=""
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="180px"
            >
              <template v-slot:placeholder>
                <v-row class="fill-height ma-0" align="center" justify="center">
                  <v-progress-circular
                    indeterminate
                    color="grey lighten-5"
                  ></v-progress-circular>
                </v-row>
              </template>
            </v-img>
            <v-card-title> Top western road trips </v-card-title>

            <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>
          </v-card>
        </v-hover> -->
      </v-slide-item>
    </v-slide-group>
  </v-container>
</template>

<script>
export default {
  props: ["title", "subTitle", "musicList"],
  data: () => ({
    model: null,
    videoId: "",
    playerVars: {
      autoplay: 1,
    },

    temp: null,
  }),
  methods: {
    playLatestMusic: function (videoId) {
      console.log("clicked play");
      const playlist = this.exposedLatestMusic.map((music) => {
        return {
          video: music.video,
          artist: music.artistId,
          title: music.title,
          singer: music.singer,
        };
      });

      this.$store.dispatch("setPlaylist", playlist);
      console.log(videoId);
      //this.$store.dispatch("setVideoId", videoId);
    },

    handleClick(music) {
      console.log("music title", music?.mTitle);
      if (this.videoId) {
        this.videoId = "";
      } else {
        this.videoId = "1mIwS4PDYwE";
        // .search(music?.mTitle)
        // .then(res => {
        //   this.videoId = res.videoId;
        //   console.log(res);
        // })
        // .catch(err => console.error(err));
      }
      this.$store.dispatch("setVideoId", this.videoId);
    },
  },
};
</script>

<style lang="scss">
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
</style>
