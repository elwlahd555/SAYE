<template>
  <v-card>
    <v-card-title class="headline pink lighten-2">
      <span class="white--text headline font-weight-black"> Bookmark </span>
    </v-card-title>
    <v-list-item-group v-model="selectedItem1" color="primary">
      <draggable
        class="dragArea"
        :list="bookmarkAlbums"
        :group="{ name: 'music', pull: 'clone', put: false }"
        :clone="cloneMusic"
      >
        <v-list-item v-for="item in bookmarkAlbums" :key="item.mId">
          <v-list-item-icon @click="delBookmark(item)">
            <v-icon color="error">mdi-heart</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title> {{ item.mTitle }}</v-list-item-title>
            <v-list-item-subtitle>{{ item.mAlbum }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </draggable>
    </v-list-item-group>
  </v-card>
</template>

<script>
import draggable from "vuedraggable";
import Swal from "sweetalert2";
import axios from "axios";

const spring_URL = process.env.VUE_APP_SPRING_URL;

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
  data() {
    return {
      selectedItem1: 1,
      uId: this.$store.state.uId
    };
  },
  methods: {
    cloneMusic(music) {
      return music;
    },
    delBookmark(bookmark) {
      console.log(bookmark);
      Swal.fire({
        title: "Are you sure?",
        text: `${bookmark.mTitle} 을(를) 북마크에서 정말 삭제할까요?`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then(result => {
        if (result.isConfirmed) {
          axios
            .delete(
              `${spring_URL}/likemusic/delete?lmMNo=${bookmark.mNo}&lmUNo=${this.uId}`
            )
            .then(res => {
              Swal.fire("Deleted!", res.data, "success");
              this.$emit("updateBookmark");
            });
        }
      });
    }
  }
};
</script>
