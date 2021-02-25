<template>
<div>
  <div class="modal-backdrop" v-bind:style="modalStyle"></div>
  <div class="modal" v-bind:style="modalStyle">
    <div class="modal-dialog modal-myiam">
      <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="btn-modal-close" v-on:click="closeModal"></button>
            <h2>IAM – 권한정책</h2>
        </div>
        <div class="modal-main">
          <ul class="list-footnote">
              <li class="txt-admin">A <span>(Admin)</span>,</li>
              <li class="txt-crud">M <span>(CRUD)</span>,</li>
              <li class="txt-read">R <span>(Read Only)</span>,</li>
              <li class="txt-excution">E <span>(Deploy 승인, Excution)</span> </li>
          </ul>
          <!-- scroll-wrap -->
          <div class="tbl-wrap scroll">
            <div class="scroll-list">
              <table class="tbl">
                <colgroup>
                  <col width="8%"/>
                  <col width="14%"/>
                  <col width="*"/>
                  <col width="13%"/>
                  <col width="5%"/>
                  <col width="5%"/>
                  <col width="5%"/>
                  <col width="9%"/>
                  <col width="7%"/>
                  <col width="5%"/>
                  <col width="8%"/>
                  <col width="5%"/>
                </colgroup>
                <thead>
                  <tr>
                    <th rowspan="2">구분</th>
                    <th rowspan="2">Role</th>
                    <th rowspan="2">Action</th>
                    <th rowspan="2">Project 범위</th>
                    <th colspan="8" class="tit-group">Toolchain 권한 </th>
                  </tr>
                  <tr>
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
                  <tr>
                    <td rowspan="12">
                        SKT <br/>
                        (Family사)
                    </td>
                    <td>*DOSS 담당자 (SKT Only)</td>
                    <td> DOSS 전체 시스템 정책 관리, 전체 기능(config. 포함) Mgt.</td>
                    <td>All</td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                  </tr>
                  <tr>
                    <td>개발 / 운영 PM</td>
                    <td>프로젝트 관리, 요구사항/일감 관리, 코드 리뷰, 배포 승인 등</td>
                    <td>직접 생성한 프로젝트</td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">A</span>(일부제한-멤버 권한 reset)</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>개발 / 운영 PL</td>
                    <td>프로젝트 관리, 요구사항/일감 관리, 코드 리뷰, 배포 승인 등</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">A</span>(일부제한-멤버 권한 reset)</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>개발(운영)자</td>
                    <td>요구사항/일감 관리, 코드 관리, CI/CD 관여 (DEV, STG)</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>App. 운영자(단순 모니터링, VOC 접수, 분석)</td>
                    <td>VoC 대응 및 일감 등록/관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>QA(Tester) </td>
                    <td>TC/결함 생성 및 관리</td>
                    <td>직접 생성한 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>Biz</td>
                    <td>요구사항/일감 관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>기획 / UX</td>
                    <td>기획/설계 산출물 관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>Deployer</td>
                    <td>일감 조회, CD 관여 (PRD)</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-excution">E</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>인프라 운영자(on-prem) </td>
                    <td>일감 관리, 변경 관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>*관리자(리더/임원)</td>
                    <td>프로젝트 현황 조회</td>
                    <td>소속 직원이 PM인 모든 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-read">R</span></td>
                  </tr>
                  <tr>
                    <td>Guest</td>
                    <td>프로젝트 현황조회</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td rowspan="9">파트너</td>
                    <td>*DOSS 담당자</td>
                    <td>DOSS 전체 시스템 정책 관리, 전체 기능 (config. 포함) Mgt.</td>
                    <td>All</td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-admin">A</span></td>
                  </tr>
                  <tr>
                    <td>개발 / 운영 PM</td>
                    <td>요구사항/일감 관리, 코드 관리, SKT 구성원 Comm.</td>
                    <td>직접 생성한 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>개발 / 운영 PL</td>
                    <td>요구사항/일감 관리, 코드 관리, SKT 구성원 Comm.</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>개발(운영)자</td>
                    <td>요구사항/일감 관리, 코드 관리, CI/CD 관여 (DEV, STG)</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-read">R</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>App. 운영자(단순 모니터링, VOC 접수, 분석)</td>
                    <td>VoC 대응 및 일감 등록/관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>QA(Tester) </td>
                    <td>TC/결함 생성 및 관리</td>
                    <td>직접 생성한 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>기획 / UX</td>
                    <td>기획/설계 산출물 관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td>Deployer</td>
                    <td>일감 조회, CD 관여 (PRD)</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-excution">E</span></td>
                    <td><span class="txt-admin">A</span></td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                  <tr>
                    <td>인프라 운영자(on-prem) </td>
                    <td>일감 관리, 변경 관리</td>
                    <td>멤버로 참여된 프로젝트</td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-admin">-</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td><span class="txt-crud">M</span></td>
                    <td>-</td>
                    <td><span class="txt-crud">M</span></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- //scroll-wrap -->
        </div>
      </div>
    </div>
  </div>
</div>
</template>
<script>
export default {
  name: "MyIAMModal",
  data() {
    return {
      modalStyle: {
        display: "none"
      }
    }
  },
  methods: {
    openModal() {
      this.$data.modalStyle = {
        display: "block"
      }
    },
    closeModal() {
      this.$data.modalStyle = {
        display: "none"
      }
    }
  }
}
</script>