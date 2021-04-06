<template>
  <v-container fill-height fluid>
    <v-row class="boardTitle">
      <v-col cols="5" align="center">
        <p>새 글 작성</p>
      </v-col>
    </v-row>

    <v-row align="center" justify="center" class="mt-10">
      <table align="center" style="width: 70%">
        <tr class="lineBoard">
          <th align="center">분류</th>
          <td>
            <v-select
              v-model="form.bClass"
              :items="category"
              menu-props="auto"
              label="Select"
              hide-details
              append-outer-icon="mdi-lightbulb-on-outline"
              outlined
              dense
            ></v-select>
          </td>
        </tr>
        <tr class="lineBoard">
          <th
            align="center"
            label-for="bTitle"
            description="게시글의 작성자익명을 입력하세요."
          >
            제 목
          </th>
          <td>
            <v-text-field
              v-model="form.bTitle"
              append-outer-icon="mdi-format-title"
              outlined
              hide-details="auto"
            ></v-text-field>
          </td>
        </tr>
        <tr class="lineBoard">
          <th algin="center">내용</th>
          <td>
            <v-textarea
              v-model="form.bContent"
              append-outer-icon="mdi-text-box-outline"
              outlined
              hide-details="auto"
            ></v-textarea>
          </td>
        </tr>
      </table>
    </v-row>

    <v-row align="center" justify="center" class="mt-10">
      <v-btn outlined color="blue" large @click="clickSubmit">
        <v-icon dark> mdi-pencil </v-icon> 글작성
      </v-btn>
      <v-btn outlined color="warning" large @click="clickReset">
        <v-icon>mdi-history</v-icon>
        취소
      </v-btn>
      <v-btn outlined color="error" large @click="$router.push('/board')">
        <v-icon>mdi-file-undo</v-icon>
        목록
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";
const spring_URL = process.env.VUE_APP_SPRING_URL;

export default {
  name: "BoardRegister",

  data() {
    return {
      user: {
        uJoindate: "string",
        uJwt: "string",
        uNickname: "string",
        uNo: 0,
        uPassword: "string",
        uProvider: "string",
        uSalt: "string"
      },
      board: {
        bWriter: "",
        bContent: "",
        bDate: "",
        bUNo: 0,
        bViewCnt: 0
      },
      form: {
        bUNo: this.$store.state.uId,
        bClass: "",
        bTitle: "",
        bWriter: "anonymous",
        bContent: ""
      },
      category: ["하소연", "도움글"]
    };
  },

  methods: {
    clickReset() {
      this.form.bClass = "";
      this.form.bTitle = "";
      this.form.bContent = "";
    },
    clickSubmit() {
      let flag = true;

      if (!this.form.bClass) {
        alert("카테고리 선택");
        flag = false;
      } else if (!this.form.bTitle) {
        alert("제목 작성");
        flag = false;
      } else if (!this.form.bContent) {
        alert("내용 작성");
        flag = false;
      }

      if (flag) {
        axios
          .post(`${spring_URL}/board/add`, this.form)
          .then(() => {
            this.$router.push({ name: "BoardList" });
          })
          .catch(error => {
            console.log(error);
          });
      }
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

.lineBoard {
  background-color: #f7c9cb;
  font-family: "Do Hyeon", sans-serif;
}
.lineBoard th {
  width: 10%;
  padding-top: 5px;
  padding-bottom: 5px;
  font-family: "Do Hyeon", sans-serif;
  font-size: 17px;
}
.lineBoard td {
  background-color: white;
  font-family: "Do Hyeon", sans-serif;
  font-size: 17px;
  width: 300px;
}

#bClass {
  width: 300px;
  padding: 5px;
  appearance: none;
}
</style>
