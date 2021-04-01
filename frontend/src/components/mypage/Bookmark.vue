<template>
  <v-card>
    <v-card-title class="pink white--text headline">
      <v-icon dark style="padding-right: 10px">
        mdi-heart
      </v-icon>
      <span> My Bookmarks</span>
    </v-card-title>

    <draggable v-model="bookmarkAlbums" tag="div">
      <v-col v-for="(bookmark, i) in bookmarkAlbums" :key="i" cols="3">
        <v-card color="pink darken-3" dark>
          <v-card-title
            class="headline"
            v-text="bookmark.mTitle"
          ></v-card-title>

          <v-card-subtitle v-text="bookmark.mArtist"></v-card-subtitle>

          <v-card-actions>
            <v-btn class="ml-2 mt-3" fab icon height="40px" right width="40px">
              <v-icon>mdi-play</v-icon>
            </v-btn>
          </v-card-actions>

          <v-avatar class="ma-3" size="125" tile>
            <v-img :src="bookmark.mImg"></v-img>
          </v-avatar>
        </v-card>
      </v-col>
    </draggable>
  </v-card>
</template>

<script>
import draggable from "vuedraggable";

export default {
  components: {
    draggable
  },
  props: {
    bookmarkAlbums: {
      type: Array,
      required: true
    }
  },
  data: () => ({})
};
</script>
