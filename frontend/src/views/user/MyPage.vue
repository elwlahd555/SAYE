<template>
  <v-container pt-5>
    <v-col cols="12">
      <PlaylistTreeview />
    </v-col>

    <v-col cols="12">
      <Bookmark
        :bookmarkAlbums="bookmarkAlbums"
        @updateBookmark="getBookmark"
      />
    </v-col>
  </v-container>
</template>

<script>
import PlaylistTreeview from "@/components/mypage/PlaylistTreeview";
import Bookmark from "@/components/mypage/Bookmark";

import { mapGetters } from "vuex";

const albumStore = "albumStore";

export default {
  components: {
    PlaylistTreeview,
    Bookmark,
  },
  computed: {
    ...mapGetters(albumStore, {
      bookmarkAlbums: "BOOKMARK_ALBUMS",
    }),
  },
  created() {
    this.getBookmark();
  },
  data: () => ({}),
  methods: {
    getBookmark() {
      this.$store.dispatch(
        albumStore + "/GET_BOOKMARK_ALBUMS",
        this.$store.state.uId
      );
    },
  },
};
</script>
