<template>
  <div>
    <table>
      <tr>
        <td>제목</td>
        <td>내용</td>
        <td>조회수</td>
      </tr>
      <tr :key="index" v-for="{ board, index } in boardList">
        <td>{{ board.bTitle }}</td>
        <td>{{ board.bContent }}</td>
        <td>{{ board.bViewCnt }}</td>
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
      boardList: {},
      board: {
        bTitle: "",
        bContent: "",
        bViewCnt: ""
      },
    };
  },
  mounted() {
    this.getBoardList();
  },
  methods: {
    getBoardList: function() {
      axios
        .get(`${spring_URL}` + "/board")
        .then(response => {
          this.boardList = response.boardList;
          console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    },
    write() {
      this.$router.push({
        path: ''
        // path: 'BoardWrite'
      })
    }
  }
};
</script>

<style></style>
