<template>
  <div class="l-header">
    <h1 class="logo"><a href="/"></a></h1>

    <dl class="align-center">
      <dt>{{this.userNm}}</dt>
      <dd>{{this.companyNm}}</dd>
      <dd>{{this.deptNm}}</dd>
      <dd><a class="btn-go" @click.prevent="doLogout" v-if="logoutBtn">로그아웃</a></dd>
    </dl>

    <div class="links">
      <a href="http://itsd.sktelecom.com" target="_blank">ITSD</a>
      <a href="javascript:void(0)" target="_blank">PCP</a>
      <a href="http://t-secu.sktelecom.com" target="_blank">정보보안포털</a>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  name : 'Header',
  components : {

  },
  data() {
    return {
      dossId : '',
      deptNm : '',
      userNm : '',
      companyNm : '',
      logoutBtn : false,
    }
  },
  methods : {
    doLogout() {
      console.log(this.$cookies.keys());
      axios
        .get("/api/logout")
        .then((res) => {
          console.log(res);
          this.$cookies.keys().forEach((cookie) => this.$cookies.remove(cookie));
          this.$store.dispatch("LOGGEDOUT");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created(){
    axios.get('/api/user/searchUserInfo/' + this.$store.state.dossId)
         .then(res => {
           this.userNm = res.data.userNm;
           this.companyNm = res.data.companyNm;
           this.deptNm = res.data.deptNm;
           this.logoutBtn = true;
          })
         .catch(error => {
           console.log(error);
          });
  },
}
</script>