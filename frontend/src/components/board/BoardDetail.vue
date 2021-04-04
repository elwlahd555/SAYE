<template>
  <div align="center">
    <v-container class="mt-3">
      <v-row>
        <v-col>
          <h3>Anonymous Board</h3>
        </v-col>
      </v-row>

      <!-- 버튼 -->
      <v-row class="mb-1">
        <v-col class="text-left">
          <v-btn outlined color="primary" :to="{ name: 'Board' }">목록</v-btn>
        </v-col>
        <v-col class="text-right">
          <v-btn
            outlined
            color="orange darken-3"
            small
            class="mr-2"
            :to="{ name: 'BoardEdit' }"
          >
            글수정
          </v-btn>
          <v-btn outlined color="error" small @click="BoardDelete"
            >글삭제</v-btn
          >
        </v-col>
      </v-row>

      <!-- 게시글 상세 카드 -->
      <v-row class="mb-1">
        <v-card class="mx-auto" color="#26c6da" dark width="700">
          <v-card-title>
            <v-icon large left> mdi-music </v-icon>
            <span class="title font-weight-light"
              >{{ board.bNo }} [{{ board.bClass }}] {{ board.bTitle }}</span
            >
          </v-card-title>

          <v-card-text class="headline font-weight-bold">
            {{ board.bContent }}
          </v-card-text>

          <v-card-actions>
            <v-list-item class="grow">
              <v-list-item-content>
                <v-list-item-title>{{ board.bWriter }}</v-list-item-title>
              </v-list-item-content>

              <v-list-item-content>
                <v-list-item-title> {{ board.bDate }}</v-list-item-title>
              </v-list-item-content>

              <v-row align="center" justify="end">
                <v-icon class="mr-1"> mdi-eye </v-icon>
                <span class="subheading mr-2">{{ board.bViewCnt }}</span>
              </v-row>
            </v-list-item>
          </v-card-actions>
        </v-card>
      </v-row>

      <comment-write />
      <comment-row
        v-for="(comment, index) in commentList"
        :key="index"
        :comment="comment"
      />
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import CommentWrite from "./comment/CommentWrite";
import CommentRow from "./comment/CommentRow";

const spring_URL = process.env.VUE_APP_SPRING_URL;
// const config = {
//   header: { "auth-token": window.localStorage.getItem("auth-token") }
// };
export default {
  name: "BoardDetail",

  components: {
    CommentWrite,
    CommentRow,
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
    };
  },

  mounted() {
    // let token = localStorage.getItem('auth-token');
    // let decode = jwt_decode(token);
    // this.user = decode.user;
    // this.editBoard();... 이렇게하나?
    this.getCommentList();
    this.getBoardDetail();
  },

  methods: {
    // BoardEdit() {
    //     this.$router.replace({
    //         name: 'board-edit',
    //         params: { bNo: this.board.bNo }
    //     });
    // },
    getBoardDetail() {
      console.log(this.$route.params.bNo);
      axios
        .get(`${spring_URL}/board/detail?bNo=${this.$route.params.bNo}`)
        .then((res) => {
          this.board = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    BoardDelete: function () {
      console.log(this.board.bNo + "번호의 글을 삭제합니다");
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
      console.log(this.board.bNo + "번호 글의 댓글리스트를 불러옵니다.");
      axios
        .get(`${spring_URL}/board/comment?cBNo=${this.$route.params.bNo}`)
        .then((res) => {
          this.commentList = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
