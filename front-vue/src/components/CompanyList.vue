<template>
<div>
  <div class="modal-backdrop" v-bind:style="modalStyle"></div>

  <div class="modal" v-bind:style="modalStyle">
    <div class="modal-dialog search-company" style="width: 420px">
      <div class="modal-content">
        <div class="modal-header">
          <h2>회사명 검색</h2>
          <button type="button" class="btn-modal-close" v-on:click="hideSearchCompanyModal"></button>
        </div>

        <div class="modal-body">
          <dl class="search-box align-center">
            <dt><label>회사명</label></dt>
            <dd>
              <div class="form-control align-center">
                <input type="text" class="" id="keyword" name="keyword" v-model="keyword" />
                <button type="button" class="icon-search" v-on:click="searchCompanyInfo"></button>
              </div>
            </dd>
          </dl>

          <!-- <company-list @sendCompanyInfo="setCompanyInfo" /> -->
          <div class="tbl-wrap">
            <div class="scroll">
              <ul>
                <li v-for="company in this.$data.companyList" v-bind:key="company.companyCd">
                  <a href="#" v-on:click="
                        setCompanyInfo(company.companyCd, company.companyName)
                      ">{{ company.companyName }}</a>
                </li>
              </ul>
            </div>
          </div>

          <div class="tbl-wrap">
            <div class="no-data">
              <p><i></i>검색결과가 없습니다. 직접 입력해 주십시오.</p>

              <button type="button" class="btn-brand" v-on:click="hideSearchCompanyModal">
                직접입력하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "CompanyList",
  data() {
    return {
      modalStyle: {
        display: "none"
      },
      keyword: "",
      companyList: [{
        companyCd: "",
        companyName: "",
        updDate: "",
        regDate: "",
      }, ],
    };
  },

  mounted() {
    axios
      .get("/api/comm/searchCompanyList", {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      })
      .then((response) => {
        console.log(response.data);
        this.$data.companyList = response.data.companyList;
      });
  },

  methods: {
    setCompanyInfo(companyCd, companyName) {
      this.$emit("setCompanyInfo", companyCd, companyName);
      this.$data.modalStyle = {
        display: "none"
      };
    },

    hideSearchCompanyModal() {
      console.log("hideSearchCompanyModal");
      this.$data.modalStyle = {
        display: "none"
      };
    },

    openModal() {
      console.log("child:showSearchCompanyModal");
      this.$data.modalStyle = {
        display: "block"
      };
    },

    searchCompanyInfo() {
      console.log("searchCompanyInfo");
      axios
        .get(
          "/api/comm/searchCompanyInfo/" +
          this.$data.keyword, {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
          }
        )
        .then((response) => {
          console.log(response.data);
          this.$data.companyList = response.data.companyList;
        });
    },
  },
};
</script>
