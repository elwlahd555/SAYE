<template>
  <v-container fluid ml-10>
    <p id="latest_music_title">{{ title }}</p>
    <p id="latest_music_subtitle">
      {{ subTitle }}
    </p>

    <v-slide-group
      v-model="model"
      active-class="red"
      center-active
      show-arrows
      dark
      style="width: 90%"
    >
      <v-slide-item
        v-for="(music, idx) in musicList"
        :key="idx"
        :music="music"
        @click="playLatestMusic"
        v-slot="{ active, toggle }"
      >
        <v-hover v-slot="{ hover }">
          <v-card
            :color="active ? 'red' : 'black'"
            class="mr-3"
            width="200"
            dark
            @click="toggle"
          >
            <v-img aspect-ratio="1" :src="music.mImg" height="180px">
              <v-expand-transition>
                <div
                  v-if="hover"
                  class="d-flex flex-row justify-center transition-fast-in-fast-out red darken-2 v-card--reveal display-1"
                  style="height: 100%"
                  @click="handleClick(music)"
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
      </v-slide-item>
    </v-slide-group>
  </v-container>
</template>

<script>
import getYouTubeID from "get-youtube-id";

export default {
  props: ["title", "subTitle", "musicList"],
  data: () => ({
    model: null,
    videoId: "",
    playerVars: {
      autoplay: 1
    },

    temp: null
  }),
  methods: {
    playLatestMusic: function(videoId) {
      console.log("clicked play");
      const playlist = this.exposedLatestMusic.map(music => {
        return {
          video: music.video,
          artist: music.artistId,
          title: music.title,
          singer: music.singer
        };
      });

      this.$store.dispatch("setPlaylist", playlist);
      console.log(videoId);
      //this.$store.dispatch("setVideoId", videoId);
    },

    handleClick(music) {
      console.log("music title", music?.mUrl);
      this.videoId = getYouTubeID(music.mUrl);
      this.$store.dispatch("setVideoId", this.videoId);
    }
  }
};
</script>
