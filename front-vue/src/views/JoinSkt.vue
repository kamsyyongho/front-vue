<script>
// import axios from "axios";

export default {
  name: "Join",
  components: {},

  created() {
    console.log("empno: " + this.$route.params);
    // console.log("empno: " + this.$route.params.b);
    // console.log("empno: " + this.$route.params.c);

    // axios
    //   .get(
    //     "http://localhost:8090/api/user/getSktMemberInfo/" +
    //       this.$route.params.empno,
    //     {
    //       headers: {
    //         "Content-Type": "application/json; charset = utf-8",
    //       },
    //     }
    //   )
    //   .then((response) => {
    //     console.log("code: " + response.data.code);
    //     console.log("msg: " + response.data.msg);
    //     console.log("result: " + JSON.stringify(response.data.result));

    //     // this.$data.signUpInfo.dossId = response.data.nextDossId;
    //     // console.log(this.$data.signUpInfo.dossId);

    //     this.$router.push({
    //       name: "JoinFido",
    //       params: {
    //         ci: this.$data.signUpInfo.ci,
    //         dossId: response.data.result.empno,
    //         userType: this.$data.signUpInfo.userType,
    //         sktEmpNo: this.$data.signUpInfo.sktEmpNo,
    //         phoneNo: this.$data.signUpInfo.phoneNo,
    //         email: this.$data.signUpInfo.email,
    //         companyCd: this.$data.signUpInfo.companyCd,
    //         companyNm: this.$data.signUpInfo.companyNm,
    //         pwd: this.$data.signUpInfo.pwd,
    //         pwdRe: this.$data.signUpInfo.pwdRe,
    //         birthDt: response.data.result.birthDt,
    //         deptNm: response.data.result.deptNm,
    //         userNm: response.data.result.hname,
    //       },
    //     });
    //   });
  },

  data() {
    return {
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
};
</script>
