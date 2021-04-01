<template>
  <div>
    <table>
      <tr>
        <td>제목</td>
        <td>내용</td>
        <td>조회수</td>
      </tr>
      <tr :key="index" v-for="{ board, index } in data">
        <td>{{ board.bClass }}</td>
        <td>{{ board.bContent }}</td>
        <td>{{ board.bView }}</td>
      </tr>
    </table>

    <button @click="write">글쓰기</button>
  </div>
</template>

<script>
import axios from "axios";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "BoardPage",
  data() {
    return {
      data: ""
    };
  },
  mounted() {
    this.getLatestMusic();
  },
  methods: {
    getLatestMusic: function() {
      axios
        .get(`${spring_URL}` + "/music/date")
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
    }
  }
};
</script>

<style></style>
