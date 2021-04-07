<template>
  <v-row class="mv-1">
    <v-col>
      <v-card border-variant="info" class="mv-2">
        <template>
          <v-row class="m-2">
            <v-col cols="2" class="text-left ml-2">
              <v-icon left>mdi-account</v-icon>
              <span>{{ comment.cWriter }}</span>
            </v-col>
            <v-col cols="3" class="text-left ml-4">
              <strong>{{ comment.cContent }}</strong>
            </v-col>
            <v-col cols="3" class="text-right mr-1">
              <v-icon left>mdi-calendar</v-icon>
              <span class="overline">{{ comment.cDate }}</span>
            </v-col>
            <v-col v-if="comment.cUNo === uId" cols="3" class="text-right mr-3">
              <v-btn
                outlined
                color="orange"
                small
                @click="editStatus = !editStatus"
                >댓글수정</v-btn
              >
              <v-btn outlined color="error" small @click="deleteComment"
                >댓글삭제</v-btn
              >
            </v-col>
          </v-row>
        </template>
        <v-card class="text-left">
          <div v-if="!editStatus">
            <!-- <strong>{{ comment.cContent }}</strong> -->
          </div>
          <div v-if="editStatus">
            <input type="text" v-model="comment.cContent" />
            <v-btn outlined color="blue" @click="editStatus = !editStatus"
              >수정하기</v-btn
            >
          </div>
        </v-card>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "commentrow",

  props: {
    comment: { type: Object, require: true },
  },

  data() {
    return {
      editStatus: false,
      uId: this.$store.state.uId,
    };
  },

  methods: {
    editComment: function () {
      axios
        .put(`${spring_URL}` + "/board/comment/edit", this.comment)
        .then((response) => {
          console.log(response.data);
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteComment: function () {
      console.log(`${this.comment}`);
      if (confirm("정말로 댓글을 삭제하시겠습니까?")) {
        axios
          .delete(
            `${spring_URL}/board/comment/delete?cBNo=${this.comment.cBNo}&cNo=${this.comment.cNo}&cUNo=${this.comment.cUNo}`
          )
          .then((response) => {
            console.log(response);
            this.$router.go(this.$router.currentRoute);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style></style>
