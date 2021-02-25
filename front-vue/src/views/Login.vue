<template>
  <div class="l-wrap">
    <div class="l-header">
      <h1 class="logo"><a href=""></a></h1>
    </div>

    <section class="page-login">
      <header class="ui-header">
        <h2>DOSS 로그인</h2>
        <p>
          기존에 사용하시던 TDE , IT Devops 아이디가 통합 됩니다. <br />
          이제 SKT 구성원은 사번으로, 파트너는 새로운 DOSS ID로 접속하세요.
        </p>
      </header>

      <main class="w-inner">
        <div class="login-box form-box">
          <form class="login-item" @submit.prevent="onSubmit">
            <div class="form-row">
              <input type="text" v-model="userId" placeholder="" required />
              <label for="userId">아이디</label>
            </div>

            <div class="form-row">
              <input type="password" v-model="userPw" placeholder="" required />
              <label for="userPw">비밀번호</label>
            </div>

            <div>
              <!-- <h3>LDAP 로그인</h3> -->
              <input
                type="checkbox"
                id="checkbox"
                v-model="checkedLDAP"
                style="vertical-align: middle"
              />
              <span style="font-size: 12px; font-weight: bold">
                LDAP 로그인</span
              >
            </div>

            <button class="btn-lg btn-brand">로그인</button>

            <div class="links">
              <a href="">아이디 찾기</a> <a href="">비밀번호 찾기</a>
              <a href="/join">회원가입</a> <a href="">FIDO 등록</a>
            </div>
          </form>

          <div class="notice login-item">
            <div class="align-center">
              <h3>DOSS 계정이 없으신가요?</h3>
              <a href="/join" class="btn-go">새 계정 만들기<i></i></a>
            </div>

            <ul>
              <li class="notice-item">
                기존 T-DE 또는 ID DevOps 사용자는 DOSS ID 발급을 <br />
                위해 최초 접속 시 기존 ID를 입력해 주세요. <br />
                DOSS ID 발급 후에는 기존 ID로 로그인 할 수 없습니다.
              </li>

              <li class="notice-item">
                구성원은 외부망에서 접근 시 FIDO인증이 필요하며,<br />
                ICT Family 및 파트너 구성원은 타인 계정 사용 방지를<br />
                위해 FIDO 인증이 필요합니다. <br />
                FIDO 인증을 위해서는 사전 등록을 해주세요.
              </li>
            </ul>
          </div>
        </div>
      </main>
    </section>

    <div class="l-footer">COPYRIGHT© SK TELECOM ALL RIGHTS RESERVED.</div>

    <FidoModal
      v-if="openFidoAuthModal"
      @close-modal="openFidoAuthModal = false"
    />
  </div>
</template>

<script>
import axios from "axios";
import FidoModal from "@/components/modal/FidoModal";

export default {
  name: "Login",
  components: {
    FidoModal,
  },
  data() {
    return {
      userId: "",
      userPw: "",
      isLdapLogin: false,
      openFidoAuthModal: false,
      checkedLDAP: false,
    };
  },
  methods: {
    onSubmit() {
      console.log("checkedLDAP=" + this.checkedLDAP);
      if (this.checkedLDAP) {
        this.isLdapLogin = true;
        this.doLogin();
      } else {
        this.doAsisLogin();
      }
    },

    doAsisLogin() {
      axios
        .post(
          "/api/user/certiAsisUser",
          {
            asisId: this.userId,
            pwd: this.userPw,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          if (res.data.code == "OK") {
            this.userId = "";
            this.userPw = "";
            alert(
              "신규 DOSS 아이디는 " +
                res.data.dossId +
                "입니다. \n초기 비밀번호는 1234 입니다."
            );
          } else {
            this.doLogin();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    doLogin() {
      console.log("isLdapLogin=" + this.isLdapLogin);
      axios
        .post(
          "/api/login",
          {
            userId: this.userId,
            userPw: this.userPw,
            isLdapLogin: this.isLdapLogin,
          },
          {
            headers: {
              "Content-Type": "application/json",
              Accept: "application/json",
            },
          }
        )
        .then((res) => {
          console.log(res);
          if (res.data.details.isSktUser) {
            // this.$store.dispatch("LOGGEDIN");
            this.$store.dispatch("LOGGEDIN", {
              dossId: res.data.details.userId,
            });
            this.$router.push("/");
          } else {
            this.doFidoAuth(res.data.details.userId);
          }
        })
        .catch((err) => {
          console.log(err);
          alert("아이디 또는 비밀번호가 일치하지 않습니다.");
        });
    },

    doFidoAuth(userId) {
      this.openFidoAuthModal = true;
      axios
        .get("/api/user/fidoAuthentication/" + userId, {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
          },
        })
        .then((res) => {
          console.log(res.data);
          if (res.data.code == "OK") {
            this.$store.dispatch("LOGGEDIN", {dossId: userId});
            this.$router.push("/");
          } else {
            alert(res.data.msg);
            this.openFidoAuthModal = false;
          }
        })
        .catch((err) => {
          alert(err);
        });
    },
  },
};
</script>
