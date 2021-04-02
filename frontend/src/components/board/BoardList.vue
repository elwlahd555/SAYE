<template>
  <div align="center">
    <div id="btnGroup" align="center">
      <button id="helpBtn" @click="helpBtn">도움글</button>
      <button id="sayBtn" @click="sayBtn">하소연</button>
    </div>
    <table style="width:40%;" align="center" id="tableBoard">
      <tr class="boardHeader">
        <th style="width:30%">제목</th>
        <th style="width:50%">내용</th>
        <th style="width:10%">조회수</th>
      </tr>
      <tr class="boardBody" :key="index" v-for="(board, index) in boardList">
        <td style="width:30%">{{ board.bTitle }}</td>
        <td style="width:50%">{{ board.bContent }}</td>
        <td style="width:10%; text-align:center;">{{ board.bViewCnt }}</td>
      </tr>
    </table>

    <button class="writeBtn" @click="write">글쓰기</button>
  </div>
</template>

<script>
import axios from 'axios';

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: 'BoardList',
  data() {
    return {
      boardList: [],
    };
  },
  mounted() {
    this.getBoardList();
  },
  methods: {
    getBoardList: function() {
      axios
        .get(`${spring_URL}` + '/board')
        .then((response) => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    helpBtn: function() {
      axios
        .get(`${spring_URL}` + '/board/helpList')
        .then((response) => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sayBtn: function() {
      axios
        .get(`${spring_URL}` + '/board/sayList')
        .then((response) => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    write() {
      this.$router.push({
        path: '',
        // path: 'BoardWrite'
      });
    },
  },
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap');

.boardHeader {
  background-color: #f7c9cb;
  font-family: 'Do Hyeon', sans-serif;
}
.boardHeader th {
  padding-top: 7px;
  padding-bottom: 7px;
  font-family: 'Do Hyeon', sans-serif;
  font-size: 17px;
}
.boardBody td {
  font-family: 'Do Hyeon', sans-serif;
  font-size: 17px;
}
.writeBtn {
  width: 43px;
  margin-top: 10px;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: 'Nanum Pen Script', cursive;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  border-radius: 5px;
}
.writeBtn:hover {
  color: white;
  background-color: #f7c9cb;
}
#helpBtn {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  margin-right: -1px;
}
#sayBtn {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
#btnGroup button {
  width: 43px;
  margin-top: 150px;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: 'Nanum Pen Script', cursive;
}
#btnGroup button:hover {
  color: white;
  background-color: #f7c9cb;
}
</style>
