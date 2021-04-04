<template>
  <v-row class="mv-3 mt-2">
    <v-col cols="11">
      <v-form-textarea
        id="comment"
        v-model="form.comment"
        placeholder="댓글 입력..."
        rows="2"
      ></v-form-textarea>
    </v-col>
    <v-col
      ><v-button outlined color="lime" @click="commentWrite">댓글등록</v-button>
    </v-col>
  </v-row>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;
const config = {
  header: { "auth-token": window.localStorage.getItem("auth-token") },
};
export default {
  name: "commentwrite",

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
    };
  },

  computed: {},
  methods: {
    commentWrite: function () {
      axios
        .post(`${spring_URL}/board/comment/add`, config)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
