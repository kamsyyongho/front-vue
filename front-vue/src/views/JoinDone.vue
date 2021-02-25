<template>
  <div class="l-wrap">
    <div class="l-header">
      <h1 class="logo"><a href=""></a></h1>
    </div>

    <section class="page-join done">
      <header class="ui-header w-inner">
        <h2>DOSS 회원가입 완료</h2>
        <p>
          <span class="txt-brand">DOSS 회원가입이 완료되었습니다.</span><br />
          발급받은 ID를 확인하시고 로그인 해 주시기 바랍니다.
        </p>
      </header>

      <main class="w-inner">
        <form class="form-box">
          <ul>
            <li>
              <!-- DOSS ID : CC00040 -->
              DOSS ID : {{ this.$route.params.dossId }}
            </li>
            <li>
              기존 ID : P000400 (IT DevOps)
            </li>
          </ul>
          <!-- <router-link to="/login">  -->
          <button type="button" class="btn-lg btn-brand" v-on:click="toLogin">
            확인
          </button>
          <!-- </router-link> -->
        </form>
      </main>
    </section>

    <div class="l-footer">
      COPYRIGHT© SK TELECOM ALL RIGHTS RESERVED.
    </div>
  </div>
</template>

<script>
export default {
  name: "JoinDone",
  components: {},

  methods: {
    toLogin() {
      this.$router.push("/login");
    },
  },
};
</script>
