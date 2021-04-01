<template>
  <v-container fluid ml-15>
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
    handleClick(music) {
      this.videoId = getYouTubeID(music.mUrl);
      this.$store.dispatch("setVideoId", this.videoId);
      this.$store.dispatch("setPlayMusic", music);
      this.$store.dispatch("addToPlaylist", music);
      //console.log("music title", music);
      //console.log(this.$store.state.asidePlaylist);
    },
  },
};
</script>
