<template>
  <v-container fluid>
    <v-row class="boardTitle">
      <v-col cols="5" align="center">
        <p>익명 게시판</p>
      </v-col>
    </v-row>

    <v-container class="my-5 pr-14">
      <!-- 버튼 -->
      <v-row>
        <v-col class="text-left">
          <v-btn outlined class="pinkBtn" :to="{ name: 'Board' }">목록</v-btn>
        </v-col>
        <v-col v-if="board.bUNo === uId" class="text-right">
          <v-btn
            outlined
            color="orange darken-3"
            class="mr-2 pinkBtn"
            :to="{ name: 'BoardEdit' }"
          >
            글수정
          </v-btn>
          <v-btn outlined color="error" class="pinkBtn" @click="BoardDelete"
            >글삭제</v-btn
          >
        </v-col>
      </v-row>

      <!-- 게시글 상세 카드 -->
      <v-row class="mb-1">
        <v-card class="mx-auto" style="width: 100%" flat>
          <v-card-subtitle>
            <v-icon large left> mdi-tag </v-icon>
            <span> {{ board.bClass }} </span>
          </v-card-subtitle>
          <v-card-title>
            <v-icon large left> mdi-subtitles </v-icon>

            <span class="title font-weight-light">{{ board.bTitle }}</span>
          </v-card-title>

          <v-divider></v-divider>
          <v-card-text class="headline font-weight-bold">
            {{ board.bContent }}
          </v-card-text>

          <v-divider></v-divider>
          <v-card-actions>
            <v-list-item>
              <v-list-item-content>
                <v-list-item-title>
                  <v-icon left> mdi-account </v-icon>
                  {{ board.bWriter }}
                </v-list-item-title>
              </v-list-item-content>

              <v-list-item-content>
                <v-list-item-title>
                  <v-icon left> mdi-calendar </v-icon>
                  {{ board.bDate }}
                </v-list-item-title>
              </v-list-item-content>

              <v-row align="center" justify="end">
                <v-icon class="mr-1"> mdi-eye </v-icon>
                <span class="subheading mr-2">{{ board.bViewCnt }}</span>
              </v-row>
            </v-list-item>
          </v-card-actions>
        </v-card>
      </v-row>

      <v-divider></v-divider>
      <br />
      <v-container>
        <v-row justify="center">
          <h2 align="center">추천 음악</h2>
        </v-row>
        <v-row justify="center">
          <h3>게시물의 감정에 따라 추천하는 음악 목록입니다.</h3>
        </v-row>
        <v-row>
          <Row title="" :musicList="recommendMusic" />
        </v-row>
      </v-container>

      <v-divider class="my-10"></v-divider>
      <p class="headline font-weight-bold">댓글</p>
      <comment-write />

      <comment-row
        v-for="(comment, index) in commentList"
        :key="index"
        :comment="comment"
        style="margin-top: 20px; width: 90%; margin: 0 auto"
      />
    </v-container>
  </v-container>
</template>

<script>
import axios from "axios";
import CommentWrite from "./comment/CommentWrite";
import CommentRow from "./comment/CommentRow";
import Row from "@/components/home/Slide0/Row.vue";

const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "BoardDetail",

  components: {
    CommentWrite,
    CommentRow,
    Row,
  },

  data() {
    return {
      board: {
        bNo: 0,
        bClass: "",
        bTitle: "",
        bContent: "",
        bWriter: "",
        bDate: "",
        bUNo: 0,
        bViewCnt: 0,
      },
      commentList: [],
      recommendMusic: [],
      emotion: "",
      uId: this.$store.state.uId,
    };
  },

  mounted() {
    this.getCommentList();
    this.getBoardDetail();
  },

  methods: {
    getBoardDetail() {
      axios
        .get(`${spring_URL}/board/detail?bNo=${this.$route.params.bNo}`)
        .then((res) => {
          this.board = res.data;
          this.testEmotion();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    BoardDelete: function () {
      //console.log(this.board.bNo + "번호의 글을 삭제합니다");
      if (confirm("정말로 삭제하시겠습니까?")) {
        axios
          .get(`${spring_URL}` + "/board/comment")
          .then((response) => {
            this.commentList = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    getCommentList: function () {
      //console.log(this.board.bNo + "번호 글의 댓글리스트를 불러옵니다.");
      axios
        .get(`${spring_URL}/board/comment?cBNo=${this.$route.params.bNo}`)
        .then((res) => {
          this.commentList = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    testEmotion() {
      console.log(this.board.bContent);
      axios
        .post(`${spring_URL}/emotion/analysis`, this.board.bContent, {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          // console.log(res.data);
          this.emotion = res.data.Result[0][1];
          console.log(this.emotion);
          axios
            .get(`${spring_URL}/music/randGenre?mEmotion=${this.emotion}`)
            .then((result) => {
              console.log(result.data);
              this.recommendMusic = result.data;
            });
        })
        .catch((error) => {
          console.log(this.query);
          console.log(error + "안됨");
        });
    },
  },
};
</script>

<style scoped>
.boardTitle {
  background-color: #e91e63;
  font-size: 2rem;
  color: white;
  font-family: "Do Hyeon", sans-serif;
}

.pinkBtn {
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  margin-right: -1px;
  width: 70px;
  border: 1px solid #f7c9cb;
  background-color: rgba(0, 0, 0, 0);
  color: #f7c9cb;
  padding: 5px;
  padding-top: 2px;
  padding-bottom: 2px;
  font-family: "Nanum Pen Script", cursive;
  font-size: 1.8rem;
}
</style>
