<template>
  <div class="l-wrap">
    <app-header />
    <div class="page-main">
      <div class="main-visual-wrap">
        <div class="main-visual">
          <div class="w-inner">
            <p class="main-visual-txt-1"></p>
            <p class="main-visual-txt-2"></p>
            <p class="main-visual-txt-3"></p>
          </div>
        </div>
      </div>
      <section class="sc-pro w-inner">
        <header class="align-center">
          <h1 class="sc-tit">참여중인 프로젝트 정보</h1>
          <div class="ml-auto">
            <a href="/my-iam" class="btn-go">MY IAM <i></i></a>&nbsp;
            <a href="javascript:void(0)" class="btn-go">프로젝트 생성변경 <i></i></a>
          </div>
        </header>
        
          <ul class="pro-list">
            <li class="pro-item" v-for="info in result" :key="info.projId">
              <div class="header-wrap">
                <div class="header">
                  <div class="header-inner">
                    <h2>{{info.projectName}}</h2>
                    <small>{{info.startDate}} ~ {{info.endDate}}</small>
                    <p v-for="role in info.roleList" :key="role.roleId">{{role.roleName}}</p>
                  </div>
                  <div class="status type-ongoing" v-if="info.projStatus === '01'">진행중</div>
                  <div class="status type-closing" v-else-if="info.projStatus === '02'">종료<br />예정</div>
                  <div class="status type-done" v-else-if="info.projStatus === '03'">종료</div>
                </div>
                <div class="sites" v-for="role in info.roleList" :key="role.roleId">
                  <!-- JUMP VR -->
                  <div v-if="info.projId === 'JUMPVR'">
                    <a :href="'/jira/projects/' + info.projId" target="_blank" class="link-jira">Jira</a>
                    <a :href="'/wiki/display/' + info.projId" target="_blank" class="link-confluence">Confluence</a>
                    <a :href="'/git/projects/' + info.projId" target="_blank" class="link-bitbucket">Bitbucket</a>

                  </div>
                  <!-- NUGU 빌링 -->
                  <div v-if="info.projId === 'BILLING'">
                    <a :href="'/jira/projects/' + info.projId" target="_blank" class="link-jira">Jira</a>
                    <a :href="'/wiki/display/' + info.projId" target="_blank" class="link-confluence">Confluence</a>
                  </div>
                  <!-- OP-ITOS-DevOps운영 -->
                  <div v-if="info.projId === 'OP030'">
                    <a :href="'/jira/projects/' + info.projId" target="_blank" class="link-jira">Jira</a>
                    <a :href="'/wiki/display/OP015'" target="_blank" class="link-confluence">Confluence</a>
                    <a :href="'/git/projects/ITSD001'" target="_blank" class="link-bitbucket">Bitbucket</a>
                    <a :href="'/jenkins1/'" target="_blank" class="link-jenkins">Jenkins</a>
                  </div>
                  <!-- <a :href="'/jira/projects/' + info.projId" target="_blank" class="link-jira" v-if="role.jira != null && role.jira != '-'">Jira</a>
                  <a :href="'/wiki/display/' + info.projId" target="_blank" class="link-confluence" v-if="role.confluence != null || role.confluence != '-'">Confluence</a>
                  <a :href="'/git/projects/' + info.projId" target="_blank" class="link-bitbucket" v-if="role.bitbucket != null || role.bitbucket != '-'">Bitbucket</a>
                  <a :href="'/jenkins1/'" class="link-jenkins" target="_blank" v-if="role.jenkins != null || role.jenkins != '-'">Jenkins</a> -->
                </div>
              </div>
              <div class="details">
                  <div class="scroll">
                      <ul class="details-list scroll-list">
                          <li class="details-item">
                              <button class="btn-sm btn-round type-ongoing">진행중</button><a href="javascript:void(0)">신규 사용자 등록 화면 설계</a>
                          </li>
                          <li class="details-item">
                              <button class="btn-sm btn-round type-ongoing">진행중</button><a href="javascript:void(0)">로그인화면 화면 설계</a>
                          </li>
                          <li class="details-item">
                              <button class="btn-sm btn-round type-ongoing">진행중</button><a href="javascript:void(0)">Demo 시나리오 화면 설계</a>
                          </li>
                      </ul>
                  </div>
              </div>
            </li>
          </ul>
      </section>
      <!-- -------------------------------- 차트 시작 ---------------------------------------- -->
      <section class="sc-dash w-inner">
        <h1 class="sc-tit">Dashboard(Sample)</h1>

        <div class="tabs">
          <a v-bind:class="[ activetab === 1 ? 'on' : '' ]" v-on:click="activetab = 1">프로젝트진척률</a>
          <a v-bind:class="[ activetab === 2 ? 'on' : '' ]" v-on:click="activetab = 2">결함발생률</a>
          <a v-bind:class="[ activetab === 3 ? 'on' : '' ]" v-on:click="activetab = 3">주단위 배포건수</a>
          <a v-bind:class="[ activetab === 4 ? 'on' : '' ]" v-on:click="activetab = 4">조직 내 프로젝트 진행건수</a>
        </div>

        <div v-bind:class="[ activetab === 1 ? '' : 'pannel' ]">
            <div class="chart-container">
                <canvas id="myChart1" width="1180" height="230"></canvas>
            </div>
        </div>
        <div v-bind:class="[ activetab === 2 ? '' : 'pannel' ]">
            <div class="chart-container">
                <canvas id="myChart2" width="1180" height="230"></canvas>
            </div>
        </div>
        <div v-bind:class="[ activetab === 3 ? '' : 'pannel' ]">
            <div class="chart-container">
                <canvas id="myChart3" width="1180" height="230"></canvas>
            </div>
        </div>
        <div v-bind:class="[ activetab === 4 ? '' : 'pannel' ]">
            <div class="chart-container">
                <canvas id="myChart4" width="1180" height="230"></canvas>
            </div>
        </div>
      </section>
      <!-- -------------------------------- 차트 끝 ---------------------------------------- -->
      <section class="sc-aside w-inner">
        <div class="notice">
          <div class="align-center">
            <h1 class="sc-tit">공지사항</h1>
            <button class="btn-more"></button>
          </div>

          <ul class="notice-list">
            <li>
              <a href="javascript:void(0)">Public Cloud 기반 통합 Toolchain 환경 구성이 완료되었습니다.</a>
              <small>2021.01.07</small>
            </li>
            <li>
              <a href="javascript:void(0)">DOSS시스템 패치 작업 공지 1.13(수) </a>
              <small>2021.01.07</small>
            </li>
            <li>
              <a href="javascript:void(0)">새롭게 선보인 DOSS 시스템에 대해 구성원 여러분들의 의견을 듣습니다.</a>
              <small>2021.01.07</small>
            </li>
          </ul>
        </div>

        <ul class="download-list">
          <li>
            <a href="javascript:void(0)">
              <p>DOSS 전사 공통가이드</p>
              <span>다운로드</span>
            </a>
          </li>

          <li>
            <a href="javascript:void(0)">
              <p>DOSS 사용자 매뉴얼</p>
              <span>다운로드</span>
            </a>
          </li>
        </ul>
      </section>
    </div>
    <app-footer />
  </div>
</template>

<script>
import Header from '@/components/include/Header.vue';
import Footer from '@/components/include/Footer.vue';

import axios from 'axios';
import Chart from 'chart.js';

export default {
  name: "Home",
  components: {
    'app-header' : Header,
    'app-footer' : Footer
  },
  data() {
    return {
      code : '',
      msg : '',
      result : null,
      userInfo : null,
      activetab: 1,
      myChart1Data : {
        type: 'bar',
        data: {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          datasets: [{
            backgroundColor: ['#e2f3dc', '#e8f1ff', '#e8f1ff', '#e7e7e7', '#e2f3dc', '#f9c8d0', '#f9c8d0', '#e8f1ff', '#e8f1ff', '#e8f1ff', '#e7e7e7', '#e2f3dc'],
            data: [20, 15, 10 , 8, 30, 40, 50, 10, 15, 10, 5, 30],
          }],
        },
        options: {
          legend: {
            display: false,
          },
          scales: {
            xAxes: [{
              maxBarThickness: 17,
              gridLines: {
                display: false,
              },
              ticks: {
                fontSize : 14,
                fontFamily:"Malgun Gothic",
                fontColor: "#333333",
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                max: 100,
                stepSize : 20,
                fontSize : 14,
                fontColor: "#333333",
              }
            }]
          }
        }
      },
      myChart2Data : {
        type: 'line',
        data: {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          datasets: [{
            backgroundColor: '#ffdab3',
            borderColor: '#ff9c34',
            borderWidth: 1,
            data: [30, 20, 10 , 8, 30, 40, 50, 10, 15, 10, 5, 30],
          }]
        },
        options: {
          legend: {
              display: false
          },
          scales: {
            xAxes: [{
              maxBarThickness: 17,
              gridLines: {
                display: false,
              },
              ticks: {
                stepSize: 1,
                fontSize : 14,
                fontFamily:"Malgun Gothic",
                fontColor: "#333333",
                beginAtZero: false,
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                max: 100,
                stepSize : 20,
                fontSize : 14,
                fontColor: "#333333",
              }
            }]
          }
        }
      },
      myChart3Data : {
        type: 'bar',
        data: {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          datasets: [{
            backgroundColor: ['#e2f3dc', '#e8f1ff', '#e8f1ff', '#e7e7e7', '#e2f3dc', '#f9c8d0', '#f9c8d0', '#e8f1ff', '#e8f1ff', '#e8f1ff', '#e7e7e7', '#e2f3dc'],
            data: [20, 15, 10 , 8, 30, 40, 50, 10, 15, 10, 5, 30],
          }]
        },
        options: {
          legend: {
            display: false,
          },
          scales: {
            xAxes: [{
              maxBarThickness: 17,
              gridLines: {
                display: false,
              },
              ticks: {
                fontSize : 14,
                fontFamily:"Malgun Gothic",
                fontColor: "#333333",
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                max: 100,
                stepSize : 20,
                fontSize : 14,
                fontColor: "#333333",
              }
            }]
          }
        }
      },
      myChart4Data : {
        type: 'line',
        data: {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
          datasets: [{
            backgroundColor: '#ffdab3',
            borderColor: '#ff9c34',
            borderWidth: 1,
            data: [30, 20, 10 , 8, 30, 40, 50, 10, 15, 10, 5, 30],
          }]
        },
        options: {
          legend: {
            display: false
          },
          scales: {
            xAxes: [{
              maxBarThickness: 17,
              gridLines: {
                display: false,
              },
              ticks: {
                fontSize : 14,
                fontFamily:"Malgun Gothic",
                fontColor: "#333333",
              }
            }],
            yAxes: [{
              ticks: {
                min: 0,
                max: 100,
                stepSize : 20,
                fontSize : 14,
                fontColor: "#333333",
              }
            }]
          }
        }
      }
    }
  },
  methods: {
    createChart(chartId, chartData) {
      const ctx = document.getElementById(chartId);
      const myChart = new Chart(ctx, {
        type: chartData.type,
        data: chartData.data,
        options: chartData.options
      });
      console.log(myChart);
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

    this.createChart('myChart1', this.myChart1Data);
    this.createChart('myChart2', this.myChart2Data);
    this.createChart('myChart3', this.myChart3Data);
    this.createChart('myChart4', this.myChart4Data);
  },
}
</script>
