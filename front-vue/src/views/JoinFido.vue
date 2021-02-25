<template>
          <div class="l-wrap" >
            <div class="l-header">
                <h1 class="logo"><a href="/login"></a></h1>
            </div>


            <section class="page-join fido">

                <header class="ui-header w-inner">
                    <h2>FIDO 등록</h2>
                    <p>FIDO인증 사용을 위해 사전 등록을 해 주세요.</p>
                </header>

                <main class="w-inner"> 
                    <div id="">
                    <!--
                    <form class="form-box" >-->
                        <ul >
                            <li>
                                <label>휴대폰 번호</label>
                            </li>
                            <li>
                               <input type="text" placeholder="휴대폰 번호"  id="phoneNo" v-model="joinFido.phoneNo"/>
                               <!--
                               <input type="text" placeholder="" v-if="joinFido.isDossId" v-model="joinFido.dossId" id="" disabled="disabled"/>
                               <input type="text" placeholder="" v-if="isActivecode" v-model="joinFido.activecode" id="" disabled="disabled"/>
                               -->
                               <div id="activeDiv">
                                    <ul>
                                        <li>{{ this.$data.joinFido.dossIdMsg }}</li> 
                                        <li>{{ this.$data.joinFido.activecodeMsg }}</li> 
                                    <!--<input type="text"  v-if="!isHidden" v-model="joinFido.dossId" id="" disabled="disabled"/>      -->
                                    <!--<input type="text"  v-if="isHidden" v-model="joinFido.activecode" id="" disabled="disabled"/>   -->   
                                    </ul>
                                </div>
                            </li>
                            <!--
                            <li>
                                   doss id : {{ this.$route.params.dossId }} 
                                   활성화코드 : {{ this.$route.params.activecode }}

                            </li>-->
                        </ul>
                        
                        <button type="botton" class="btn-lg btn-brand" v-on:click="regFido">
                            FIDO 등록
                        </button>
                        
                    <!--</form>-->
                    </div>
                </main>

            </section>

            <div class="l-footer">
                COPYRIGHT© SK TELECOM ALL RIGHTS RESERVED.
            </div>
            
        </div>
        
</template>

<script>
import axios from "axios";

export default {
  name: "JoinFido",
  components: {},
  data (){
      return{
          joinFido : {
              ci: "",
              dossId: "",
              userType: "",
              sktEmpNo: "",
              phoneNo: "",
              email: "",
              companyCd: "",
              companyNm: "",
              pwd: "",
              birthDt: "",
              deptNm: "",
              userNm: "",
              dossIdMsg : "",
              activecode : "",
              activecodeMsg : "",
              revSession : ""
          }
      }
  },
  methods : {
    regFido(){

        //callback에서 지속적으로 연결됨
        this.isRegPushInfo();

        //this.signUp();
    },

    //fido 등록 여부 확인
    isRegPushInfo(){
        
        console.log(" == isRegPushInfo == ");
        //this.$data.joinFido.dossId = this.$route.params.dossId;

        axios
            .get('/api/fido/isRegPushInfo/'+this.$data.joinFido.dossId, {
                headers: {
                    "Content-Type": "application/json; charset = utf-8",
                },
            })
            .then((response) => {
               if(response.data.result.code=="OK"){
                   this.getActiveCode();
               }else{
                   alert(response.data.result.msg);
               }     
               
            }) 
            .catch((err)=>{
                alert(" fido 등록 여부 확인 중 오류 발생 \n msg : " + err.message ); 
                console.log(err.config);
            })
    },
    
    //활성화 코드 요청
    
    getActiveCode(){

        //let waitMobileUse = this.waitMobileUse();
        
        console.log(" == getActiveCode == ");
        
        axios
        .post(
            "/api/fido/getActiveCode",
            JSON.stringify(this.$data.joinFido),
                {
                    headers: {
                    "Content-Type": "application/json; charset = utf-8",
                    Accept: "application/json; charset = utf-8",
                    },
                }
            )
        .then(response=>{
            console.log(response);

            if(response.data.result.code=="OK"){
                console.log(response.data.result.activecode + " get activecode");
                
                this.$data.joinFido.activecode = response.data.result.activecode;
                this.$data.joinFido.revSession  = response.data.result.revSession;

                console.log(" ===act resp revSession== " + this.$data.joinFido.revSession);
                console.log(" ===act resp activecode== " + this.$data.joinFido.activecode);

                this.waitMobileUse();    
                alert("skt fido > 기기등록에서 아이디/활성화 코드를 입력해주세요.");

                this.$data.joinFido.dossIdMsg =     "DossId : " + this.$data.joinFido.dossId;
                this.$data.joinFido.activecodeMsg = "활성화코드 : " + this.$data.joinFido.activecode;                                        
            }    
        })
            
    },
    //기기등록 대기
    waitMobileUse(){
       
        console.log(" ===act waitMobileUse bef revSession== " + this.$data.joinFido.revSession);
        console.log(" ===act waitMobileUse bef activecode== " + this.$data.joinFido.activecode);
        console.log(" ===act waitMobileUse bef dossId== " + this.$data.joinFido.dossId);
        
        axios
        .post(
            "/api/fido/waitMobileUse",
            JSON.stringify(this.$data.joinFido),
                {
                    headers: {
                    "Content-Type": "application/json; charset = utf-8",
                    Accept: "application/json; charset = utf-8",
                    },
                }
            )
        .then(response=>{
            console.log(response + " == waitMobileUse response == ");
            if(response.data.result.code=="OK"){
                alert("기기등록이 완료되었습니다. 사용자 등록을 실행합니다.");
                this.registration();
            }else{
                alert(response.data.result.msg);
            }   
        }) 
         
    },

    //인증방식 등록
    registration(){
        console.log(" == registration == ");

        axios
            .get('/api/fido/registration/'+this.$data.joinFido.dossId, {
                headers: {
                    "Content-Type": "application/json; charset = utf-8",
                },
            })
            .then((response) => {
               if(response.data.result.code=="OK"){
                   alert("fido 인증 등록이 완료되었습니다.");

                   this.signUp();
               }else{
                   alert(response.data.result.msg);
               }     
               
            })       
    },
    signUp(){
        console.log(this.$data.joinFido.companyNm + " : companyNm ");

        axios
        .post(
            "/api/user/signUp",
            JSON.stringify(this.$data.joinFido),
            {
            headers: {
                "Content-Type": "application/json; charset = utf-8",
                Accept: "application/json; charset = utf-8",
            },
            }
        )
        .then((response) => {
            console.log(response.data);
            if(response.data.code=="OK"){
                alert("회원가입이 완료되었습니다.");
                this.$router.push({
                    name: "JoinDone",
                    params: { dossId: this.$data.joinFido.dossId },
                });
            }else{
                alert(response.data.msg);
            }

        });        
    },
    setData(){
        this.$data.joinFido.dossId = this.$route.params.dossId;
        this.$data.joinFido.userType = this.$route.params.userType; 
        this.$data.joinFido.sktEmpNo = this.$route.params.sktEmpNo; 
        this.$data.joinFido.phoneNo = "";
        this.$data.joinFido.email = this.$route.params.email;
        this.$data.joinFido.companyCd = this.$route.params.companyCd;
        this.$data.joinFido.companyNm = this.$route.params.companyNm;
        this.$data.joinFido.pwd = this.$route.params.pwd;
        this.$data.joinFido.birthDt = this.$route.params.birthDt;
        this.$data.joinFido.deptNm = this.$route.params.deptNm;
        this.$data.joinFido.userNm  = this.$route.params.userNm;
        this.$data.joinFido.dossIdMsg ="";
        this.$data.joinFido.activecode = "";
        this.$data.joinFido.activecodeMsg = "";
        this.$data.joinFido.revSession = "";
    },
  },

  created(){
        this.setData();
        console.log(this.$data.joinFido.dossId + " ...?? "); 
  },
  
  
} 
</script>