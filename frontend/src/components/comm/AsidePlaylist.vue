<template>
  <v-container fluid>
    <v-virtual-scroll :items="playlist" :item-height="50" height="250">
      <template v-slot:default="{ item }">
        <v-list-item>
          <v-list-item-avatar>
            <v-avatar size="56">
              <img :src="item.mImg" alt="albumImg" />
            </v-avatar>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{ item.mTitle }}</v-list-item-title>
          </v-list-item-content>

          <v-list-item-action>
            <v-btn icon @click="onClickDelt(item)">
              <v-icon color="red accent-3">
                mdi-minus
              </v-icon>
            </v-btn>
          </v-list-item-action>
          <v-list-item-action>
            <v-btn icon @click="handleClick(item)">
              <v-icon color="red darken-4" class="mr-2"> mdi-play </v-icon>
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </template>
    </v-virtual-scroll>
  </v-container>
</template>

<script>
import getYouTubeID from "get-youtube-id";

import { mapState } from "vuex";

export default {
  props: ["playlist"],
  data: () => ({}),
  computed: {
    ...mapState({
      asidePlaylist: "asidePlaylist"
    })
  },

  methods: {
    onClickDelt(music) {
      this.$store.dispatch("delMusicFromPlaylist", music);
    },
    handleClick(music) {
      this.videoId = getYouTubeID(music.mUrl);
      this.$store.dispatch("setVideoId", this.videoId);
      const currentVideoIndex = this.asidePlaylist
        .map(item => item.mId)
        .indexOf(music.mId);

      this.$store.dispatch(
        "setPlayMusic",
        this.asidePlaylist[currentVideoIndex]
      );
    }
  }
};
</script>
