<template>
  <v-card>
    <v-card-title class="pink white--text headline">
      <v-icon dark style="padding-right: 10px">
        mdi-heart
      </v-icon>
      <span> My Bookmarks</span>
    </v-card-title>

    <draggable v-model="bookmarkAlbums" tag="v-row">
      <v-col v-for="(bookmark, i) in bookmarkAlbums" :key="i" cols="3">
        <v-card color="pink darken-3" dark>
          <v-card-title class="headline" v-text="bookmark.title"></v-card-title>

          <v-card-subtitle v-text="bookmark.artistName"></v-card-subtitle>

          <v-card-actions>
            <v-btn class="ml-2 mt-3" fab icon height="40px" right width="40px">
              <v-icon>mdi-play</v-icon>
            </v-btn>
          </v-card-actions>

          <v-avatar class="ma-3" size="125" tile>
            <v-img :src="bookmark.artworkUrl100"></v-img>
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
  data: () => ({
    items: [
      {
        color: "#1F7087",
        src: "https://cdn.vuetifyjs.com/images/cards/foster.jpg",
        title: "Supermodel",
        artist: "Foster the People"
      },
      {
        color: "#952175",
        src: "https://cdn.vuetifyjs.com/images/cards/halcyon.png",
        title: "Halcyon Days",
        artist: "Ellie Goulding"
      }
    ]
  })
};
</script>
