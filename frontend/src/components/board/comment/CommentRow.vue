<template>
  <v-row class="mv-1">
    <v-col>
      <v-card border-variant="info" class="mv-2" no-body>
        <template>
          <v-row class="m-1">
            <v-col class="text-left ml-3"
              ><strong>{{ cWriter }}</strong> {{ cDate }}
            </v-col>
            <v-col class="text-right mr-3">
              <v-button variant="link" @click="editBtn">댓글수정</v-button>
              <v-button variant="link" @click="deleteComment"
                >댓글삭제</v-button
              >
            </v-col>
          </v-row>
        </template>
        <v-card-body class="text-left">
          <div v-if="!editStatus">
            {{ cContent }}
          </div>
          <div v-if="editStatus">
            <input type="text" v-model="form.cContent" />
            <v-button variant="link" @click="editComment">수정하기</v-button>
          </div>
        </v-card-body>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;
const config = {
  headers: { "auth-token": window.localStorage.getItem("auth-token") },
};

export default {
  name: "commentrow",

  props: {
    cNo: { type: Number },
    cBNo: { type: Number },
    cWriter: { type: String },
    cContent: { type: String },
    cDate: { type: String },
    cUNo: { type: Number },
  },

  data() {
    return {
      form: {
        cNo: 0,
        cBNo: 0,
        cWriter: "",
        cContent: "",
        cDate: "",
        cUNo: 0,
      },
      editStatus: false,
    };
  },

  methods: {
    editBtn() {
      if (this.editStatus) {
        this.editStatus = false;
      } else {
        this.form.cContent = `${this.cContent}`;
        this.editStatus = true;
      }
    },
    editComment: function () {
      axios
        .put(`${spring_URL}` + "/board/comment/edit", this.form, config)
        .then((response) => {
          console.log(response);
          this.$router.go(this.$router.currentRoute);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteComment: function () {
      console.log(`${this.cBNo}` + "글의 댓글" + `${this.cNo}` + "삭제합니다");
      if (confirm("정말로 댓글을 삭제하시겠습니까?")) {
        axios
          .delete(`${spring_URL}` + "/board/comment/delete")
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
