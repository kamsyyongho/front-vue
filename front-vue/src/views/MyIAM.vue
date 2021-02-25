<template>
  <div class="l-wrap">
    <app-header />
    <section class="page-myiam">

      <header class="ui-main-header">
        <h2>MY IAM</h2>
        <p>내가 참여한 프로젝트와 나의그룹의 정보 및 권한을 확인 할 수 있습니다.</p>
      </header>

      <div class="ui-main">

        <div class="tabs">
          <a v-bind:class="[ activetab === 1 ? 'on' : '' ]" v-on:click="activetab = 1">나의 프로젝트</a>
          <a v-bind:class="[ activetab === 2 ? 'on' : '' ]" v-on:click="activetab = 2">나의 그룹</a>
        </div>

        <div class="pannel" id="tab1" v-show="activetab === 1">
          <!-- tbl-row -->
          <div class="tbl-row">
            <div class="tbl-wrap">
              <table class="tbl">
                <colgroup>
                  <col width="5%"/>
                  <col width="30%"/>
                  <col width="15%"/>
                  <col width="10%"/>
                  <col width="30%"/>
                  <col width="30%"/>
                  <col width="10%"/>
                </colgroup>
                <thead>
                  <tr>
                    <th>NO</th>
                    <th>프로젝트명</th>
                    <th>담당자</th>
                    <th>유형</th>
                    <th>사용Tool</th>
                    <th>기간(상태)</th>
                    <th>&nbsp;</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(info, index) in result" :key="info.projId">
                    <td>{{result.length - index}}</td>
                    <td class="txt-left"><a href="">{{info.projectName}}</a></td>
                    <td>{{info.prjMstCompNm}}&nbsp;{{info.prjMstUserNm}}님</td>
                    <td>{{info.pmsType}}</td>
                    <td id="role">
                      <div v-for="role in info.roleList" :key="role.roleId">
                        {{role.portal != null && role.portal != '-' ? 'Portal,' : ''}}
                        {{role.crowd != null && role.crowd != '-' ? 'Crowd,' : ''}}
                        {{role.jira != null && role.jira != '-' ? 'Jira,' : ''}}
                        {{role.confluence != null && role.confluence != '-' ? 'Confluence,' : ''}}
                        {{role.bitbucket != null && role.bitbucket != '-' ? 'Bitbucket,' : ''}}
                        {{role.jenkins != null && role.jenkins != '-' && info.projId != 'OP015' ? 'Jenkins,' : ''}}
                        {{role.sonarqube != null && role.sonarqube != '-' ? 'Sonarqube,' : ''}}
                        {{role.nexus != null && role.nexus != '-' ? 'Nexus' : ''}}
                      </div>
                    </td>
                    <td>{{info.startDate}}~{{info.endDate}}({{info.projStatusNm}})</td>
                    <td><button v-on:click="searchProjectUserList(info.projId)">조회</button></td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- pagination -->
            <nav class="pagination">
              <div class="justify-center">
                <a class="btn-paging btn-prev-10" href="#"></a>
                <a class="btn-paging btn-prev" href="#"></a>

                <a class="page-link on" href="#">1</a>

                <a class="btn-paging btn-next" href="#"></a>
                <a class="btn-paging btn-next-10" href="#"></a> 
              </div>
            </nav>
            <!-- //pagination -->
        </div>
        <!-- //tbl-row -->

        <!-- tbl-row -->
        <div class="tbl-row" v-show="projectUserList != null">
          <div class="tbl-wrap">
            <table class="tbl">
              <colgroup>
                <col width="5%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="*"/>
                <col width="10%"/>
                <col width="5%"/>
                <col width="5%"/>
                <col width="5%"/>
                <col width="6%"/>
                <col width="6%"/>
                <col width="5%"/>
                <col width="5%"/>
                <col width="5%"/>
              </colgroup>
              <thead>
                <tr>
                  <th>NO</th>
                  <th>이름</th>
                  <th>회사</th>
                  <th>조직</th>
                  <!-- 2021-02-02 -->
                  <th>권한 <button class="btn-popup" v-on:click="$refs.refmodal.openModal()"></button></th>
                  <!-- //2021-02-02 -->
                  <th>Portal</th>
                  <th>Crowd</th>
                  <th>Jira</th>
                  <th>Confluence</th>
                  <th>Bitbucket</th>
                  <th>Jenkins</th>
                  <th>Sonarqube</th>
                  <th>Nexus</th>
                </tr>
              </thead>

              <tbody>
                <tr v-for="(list, index) in projectUserList" :key="list.userNm">
                  <td>{{projectUserList.length - index}}</td>
                  <td><a href="">{{list.userNm}}</a></td>
                  <td>{{list.companyNm}}</td>
                  <td>{{list.deptNm}}</td>
                  <td>{{list.roleName}}</td>
                  <td><span :class="projectRoleClassName(list.portal)">{{list.portal}}</span></td>
                  <td><span :class="projectRoleClassName(list.crowd)">{{list.crowd}}</span></td>
                  <td><span :class="projectRoleClassName(list.jira)">{{list.jira}}</span></td>
                  <td><span :class="projectRoleClassName(list.confluence)">{{list.confluence}}</span></td>
                  <td><span :class="projectRoleClassName(list.bitbucket)">{{list.bitbucket}}</span></td>
                  <td><span :class="projectRoleClassName(list.jenkins)">{{list.jenkins}}</span></td>
                  <td><span :class="projectRoleClassName(list.sonarqube)">{{list.sonarqube}}</span></td>
                  <td><span :class="projectRoleClassName(list.nexus)">{{list.nexus}}</span></td>
                </tr>
              </tbody>
            </table>
          </div>

            <ul class="list-footnote">
                <li class="txt-admin">A <span>(Admin)</span>,</li>
                <li class="txt-crud">M <span>(CRUD)</span>,</li>
                <li class="txt-read">R <span>(Read Only)</span>,</li>
                <li class="txt-excution">E <span>(Deploy 승인, Excution)</span> </li>
            </ul>
          </div>
          <!-- //tbl-row -->
        </div>

        <div class="pannel" id="tab2" v-show="activetab === 2">
          탭2 내용이 들갑니다.
        </div>
      </div>
      
    </section>

    <my-iam-modal ref="refmodal" />

    <app-footer />
  </div>
</template>

<script>
import Header from '@/components/include/Header.vue';
import Footer from '@/components/include/Footer.vue';
import MyIAMModal from "@/components/modal/MyIAMModal.vue";

import axios from 'axios';

export default {
  name: "MyIAM",
  components: {
    'app-header' : Header,
    'app-footer' : Footer,
    'my-iam-modal' : MyIAMModal
  },
  data() {
    return {
      code : '',
      msg : '',
      result : null,
      activetab: 1,
      projectUserList : null,
      modalStyle: { display: "block" },
    }
  },
  methods: {
    showModal() {
      this.$data.modalStyle = { display: "block" };
      console.log(this.$data.modalStyle);
    },
    searchProjectUserList(projectId) {
      this.projectUserList = null;
      axios.get('/api/project/project-user/' + projectId)
         .then(res => {
           this.code = res.data.code;
           this.msg = res.data.msg;
           this.projectUserList = res.data.result;
          })
         .catch(error => {
           console.log(error);
          });
    },
    projectRoleClassName(value){
      let className;
      if(value === 'A'){
        className = 'txt-admin';
      } else if(value === 'M'){
        className = 'txt-crud';
      } else if(value === 'R'){
        className = 'txt-read';
      } else if(value === 'E'){
        className = 'txt-excution';
      } else {
        className = '';
      }
      return className;
    }
  },
  mounted() {
    axios.get('/api/project/my-project')
         .then(res => {
           this.code = res.data.code;
           this.msg = res.data.msg;
           this.result = res.data.result;
          })
         .catch(error => {
           console.log(error);
          });
  }
}
</script>

<style>
  #role {
    overflow:hidden;
    text-overflow:ellipsis;
    white-space:nowrap;
  }
  #role div {
    font-size: 14px;
  }
</style>