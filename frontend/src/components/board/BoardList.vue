<template>
  <v-container fluid>
    <v-row class="boardTitle">
      <v-col cols="5" align="center">
        <p>익명 게시판</p>
      </v-col>
    </v-row>

    <div align="center">
      <div id="btnGroup" align="center">
        <button id="allBtn" @click="allBtn">전체</button>
        <button id="helpBtn" @click="helpBtn">도움글</button>
        <button id="sayBtn" @click="sayBtn">하소연</button>
      </div>
      <table style="width: 70%" align="center" id="tableBoard">
        <caption>
          Board List Information
        </caption>
        <tr class="boardHeader">
          <th id="bNo" style="width: 10%">글번호</th>
          <th id="bTitle" style="width: 50%">제목</th>
          <th id="bView" style="width: 10%">조회수</th>
        </tr>
        <tr
          class="boardBody"
          :key="index"
          v-for="(board, index) in boardList"
          striped
          hover
          :item="board"
          @click="detailBoard(board)"
        >
          <td style="width: 10%; text-align: center">{{ board.bNo }}</td>
          <td style="width: 50%">{{ board.bTitle }}</td>
          <td style="width: 10%; text-align: center">{{ board.bViewCnt }}</td>
        </tr>
      </table>

      <button class="writeBtn" @click="toWritePage">
        <v-icon color="#f7c9cb"> mdi-pencil </v-icon>
        글쓰기
        <v-icon color="#f7c9cb"> mdi-pencil </v-icon>
      </button>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "BoardList",
  data() {
    return {
      boardList: []
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
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    allBtn() {
      axios
        .get(`${spring_URL}` + "/board")
        .then(response => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    helpBtn: function() {
      axios
        .get(`${spring_URL}` + "/board/helpList")
        .then(response => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    sayBtn: function() {
      axios
        .get(`${spring_URL}` + "/board/sayList")
        .then(response => {
          this.boardList = response.data;
          // console.log(this.boardList);
        })
        .catch(error => {
          console.log(error);
        });
    },
    detailBoard(item) {
      console.log(item.bNo + "의 글 보기");
      this.$router.replace({
        name: "BoardDetail",
        params: { bNo: item.bNo }
      });
    },
    toWritePage() {
      this.$router.replace({
        name: "BoardRegister"
      });
    }
  }
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap");

.boardTitle {
  background-color: #e91e63;
  font-size: 2rem;
  color: white;
  font-family: "Do Hyeon", sans-serif;
}

.boardHeader {
  background-color: #f7c9cb;
  font-family: "Do Hyeon", sans-serif;
}
.boardHeader th {
  padding-top: 7px;
  padding-bottom: 7px;
  font-family: "Do Hyeon", sans-serif;
  font-size: 1.3rem;
}
.boardBody td {
  font-family: "Do Hyeon", sans-serif;
  font-size: 1.6rem;
}
.writeBtn {
  width: 120px;
  margin-top: 10px;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: "Nanum Pen Script", cursive;
  font-size: 1.8rem;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  border-radius: 5px;
}
.writeBtn:hover {
  color: white;
  background-color: #f7c9cb;
}
#allBtn {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
}
#helpBtn {
  margin-right: -1px;
  margin-left: -0.8px;
}
#sayBtn {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}
#btnGroup button {
  width: 70px;
  margin-top: 50px;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: "Nanum Pen Script", cursive;
  font-size: 1.8rem;
}
#btnGroup button:hover {
  color: white;
  background-color: #f7c9cb;
}
tr.boardBody:hover {
  background-color: #f7c9cb62;
}
</style>
