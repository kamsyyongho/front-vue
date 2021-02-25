<template>
  <div id="app">
    <FidoModal v-if="isModalViewed" @close-modal="isModalViewed = false" />
  </div>
</template>

<script>
import axios from "axios";
import FidoModal from "@/components/modal/FidoModal";

export default {
  name: "FidoAuth",
  components: {
    FidoModal,
  },
  data() {
    return {
      isModalViewed: false,
    };
  },

  created() {
    this.isModalViewed = true;
  },

  mounted() {
    axios
      .get(
        "/api/user/fidoAuthentication/" +
          this.$route.params.dossId,
        {
          headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
          },
        }
      )
      .then((res) => {
        console.log(res.data);
        if (res.data.code == "OK") {
          console.log("FidoAuth Completed.");
          // this.$store.commit("usernamePasswordAuthenticated");
          this.$router.push({ name: "Home" });
        } else {
          console.log("FidoAuth Failed.");
          alert("FIDO 인증을 실패하였습니다. 다시 로그인을 시도해주세요.");
          window.location.href = "/logout";
        }
      })
      .catch((err) => {
        console.log("FidoAuth err: " + err);
        alert("FIDO 인증을 실패하였습니다. 다시 로그인을 시도해주세요.");
        window.location.href = "/logout";
      });
  },

  methods: {},
};
</script>