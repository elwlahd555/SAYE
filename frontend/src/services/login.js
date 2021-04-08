import axios from "@/services/axios";

const server_URL = process.env.VUE_APP_SERVER_URL;

const kakaoHeader = {
  Authorization: "1e4d6ec5ac4ae596f29fc38663d8a0ed",
  "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
};

const getKakaoToken = async code => {
  console.log("loginWithKakao");
  try {
    const data = {
      grant_type: "authorization_code",
      client_id: "e000fe4772870c3a157787b3e779e2ce",
      redirect_uri: `${server_URL}/kakaocallback`,
      code: code
    };
    const queryString = Object.keys(data)
      .map(k => encodeURIComponent(k) + "=" + encodeURIComponent(data[k]))
      .join("&");
    const result = await axios.post(
      "https://kauth.kakao.com/oauth/token",
      queryString,
      { headers: kakaoHeader }
    );
    return result;
  } catch (e) {
    return e;
  }
};

const getKakaoUserInfo = async () => {
  let data = "";
  await window.Kakao.API.request({
    url: "/v2/user/me",
    success: function(response) {
      data = response;
    },
    fail: function(error) {
      console.log(error);
    }
  });
  return data;
};

export { getKakaoToken, getKakaoUserInfo };
