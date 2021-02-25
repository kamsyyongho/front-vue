<template>
  <div class="l-wrap">
    <div class="l-header">
      <h1 class="logo"><a href=""></a></h1>
    </div>

    <section class="page-join register">
      <header class="ui-header w-inner">
        <h2>가입하기</h2>
        <strong>이미 사용중인 DOSS계정이 있습니까?</strong>
        <a href="/login" class="btn-go">DOSS 계정으로 로그인하기<i></i></a>
      </header>

      <main class="w-inner">
        <form class="form-box">
          <div class="form-row">
            <input
              type="text"
              name="userNm"
              id="userNm"
              v-model="signUpInfo.userNm"
              placeholder=""
              required
            />
            <label for="userNm">이름</label>
            <button class="btn-fn btn-outline-brand">본인인증</button>
          </div>

          <div class="form-row">
            <input
              type="text"
              name="companyNm"
              id="companyNm"
              v-model="signUpInfo.companyNm"
              placeholder=""
              required
            />
            <label for="companyNm">회사명</label>
            <button
              class="btn-fn btn-outline-secondary"
              name="btnSearchCompany"
              id="btnSearchCompany"
              v-on:click="$refs.refmodal.openModal()"
            >
              회사검색
            </button>
            <input
              type="hidden"
              name="companyCd"
              id="companyCd"
              placeholder=""
              required
            />
          </div>

          <div class="form-row">
            <input
              type="text"
              name="deptNm"
              id="deptNm"
              v-model="signUpInfo.deptNm"
              placeholder=""
              required
            />
            <label for="deptNm">부서명</label>
          </div>

          <div class="form-row">
            <input
              type="text"
              name="email"
              id="email"
              v-model="signUpInfo.email"
              placeholder=""
              required
            />
            <label for="email">이메일</label>
          </div>

          <div class="form-row">
            <input
              type="password"
              name="pwd"
              id="pwd"
              v-model="signUpInfo.pwd"
              placeholder=""
              required
            />
            <label for="pwd1">비밀번호</label>
          </div>

          <div class="form-row">
            <input
              type="password"
              name="pwdRe"
              id="pwdRe"
              v-model="signUpInfo.pwdRe"
              placeholder=""
              required
            />
            <label for="pwdRe">비밀번호 확인</label>
          </div>

          <button type="button" class="btn-lg btn-brand" v-on:click="reqJoin">
            다음
          </button>
        </form>
      </main>
    </section>

    <div class="l-footer">
      COPYRIGHT© SK TELECOM ALL RIGHTS RESERVED.
    </div>
    <company-list ref="refmodal" @setCompanyInfo="setCompanyInfo" />
  </div>
</template>

<script>
import axios from "axios";
import CompanyList from "@/components/CompanyList.vue";

export default {
  name: "Join",
  components: {
    CompanyList
  },

  data() {
    return {
      validated: false,
      modalStyle: { display: "block" },
      signUpInfo: {
        ci: "2313123112",
        dossId: "",
        userType: "S",
        sktEmpNo: "P321321",
        phoneNo: "010-2223-1231",
        email: "",
        companyCd: "",
        companyNm: "",
        pwd: "",
        pwdRe: "",
        birthDt: "12345678",
        deptNm: "",
        userNm: "",
      },
    };
  },

  methods: {
    reqJoin() {
      if (
        this.$data.signUpInfo.userNm == "" ||
        this.$data.signUpInfo.companyNm == "" ||
        this.$data.signUpInfo.deptNm == "" ||
        this.$data.signUpInfo.email == "" ||
        this.$data.signUpInfo.pwd == "" ||
        this.$data.signUpInfo.pwdRe == ""
      ) {
        alert("모든 항목을 입력하세요.");
        return false;
      }

      if (this.$data.signUpInfo.pwd != this.$data.signUpInfo.pwdRe) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
      }

      console.log("reqJoin");
      axios
        .get("/api/user/makeDossId", {
          headers: {
            "Content-Type": "application/json; charset = utf-8",
          },
        })
        .then((response) => {
          console.log(response.data.nextDossId);
          this.$data.signUpInfo.dossId = response.data.nextDossId;
          console.log(this.$data.signUpInfo.dossId);

          this.$router.push({
            name: "JoinFido",
            params: {
              ci: this.$data.signUpInfo.ci,
              dossId: this.$data.signUpInfo.dossId,
              userType: this.$data.signUpInfo.userType,
              sktEmpNo: this.$data.signUpInfo.sktEmpNo,
              phoneNo: this.$data.signUpInfo.phoneNo,
              email: this.$data.signUpInfo.email,
              companyCd: this.$data.signUpInfo.companyCd,
              companyNm: this.$data.signUpInfo.companyNm,
              pwd: this.$data.signUpInfo.pwd,
              pwdRe: this.$data.signUpInfo.pwdRe,
              birthDt: this.$data.signUpInfo.birthDt,
              deptNm: this.$data.signUpInfo.deptNm,
              userNm: this.$data.signUpInfo.userNm,
            },
          });

          /*
          axios
            .post(
              "/api/user/signUp",
              JSON.stringify(this.$data.signUpInfo),
              {
                headers: {
                  "Content-Type": "application/json; charset = utf-8",
                  Accept: "application/json; charset = utf-8",
                },
              }
            )
            .then((response) => {
              console.log(response.data);
              this.$router.push({
                name: "JoinDone",
                params: { dossId: this.$data.signUpInfo.dossId },
              });
            });

          */
        });
    },

    showSearchCompanyModal() {
      console.log("showSearchCompanyModal");
      this.$data.modalStyle = { display: "block" };
      console.log(this.$data.modalStyle);
    },

    setCompanyInfo(companyCd, companyName) {
      this.$data.signUpInfo.companyNm = companyName;
      this.$data.signUpInfo.companyCd = companyCd;
    },
  },
};
</script>
