package com.example.baeyongpyo.oauthlogin.LoginModule.LoginAdapter

import android.content.Context
import com.kakao.auth.*

class KakaoSDKAdpater(private val context : Context) : KakaoAdapter(){
    override fun getApplicationConfig(): IApplicationConfig = IApplicationConfig { context }


    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig{
            /**Kakao SDK 에서 사용되는 WebView에서 email 입력폼에서 data를 save할지여부를 결정한다. Default true.*/
            override fun isSaveFormData(): Boolean = true

            /** 로그인시 인증받을 타입을 지정한다. 지정하지 않을 시 가능한 모든 옵션이 지정된다.
             * KAKAO_TALK       	            0	kakaotalk으로 login을 하고 싶을때 지정.
             * KAKAO_STORY	                    1	kakaostory으로 login을 하고 싶을때 지정.
             * KAKAO_ACCOUNT	                2	웹뷰 Dialog를 통해 카카오 계정연결을 제공하고 싶을경우 지정.
             * KAKAO_TALK_EXCLUDE_NATIVE_LOGIN	3	카카오톡으로만 로그인을 유도하고 싶으면서 계정이 없을때 계정생성을 위한 버튼도 같이 제공을 하고 싶다면 지정.
             *                                      KAKAO_TALK과 중복 지정불가.
             * KAKAO_LOGIN_ALL	                4	모든 로그인방식을 사용하고 싶을때 지정 */
            override fun getAuthTypes(): Array<AuthType> = arrayOf(AuthType.KAKAO_LOGIN_ALL)

            /**로그인시 access token과 refresh token을 저장할 때의 암호화 여부를 결정한다.*/
            override fun isSecureMode(): Boolean = false

            /**일반 사용자가 아닌 Kakao와 제휴된 앱에서 사용되는 값으로, 값을 채워주지 않을경우 ApprovalType.INDIVIDUAL 값을 사용하게 된다.*/
            override fun getApprovalType(): ApprovalType = ApprovalType.INDIVIDUAL

            /** SDK 로그인시 사용되는 WebView에서 pause와 resume시에 Timer를 설정하여 CPU소모를 절약한다.
             * true 를 리턴할경우 webview로그인을 사용하는 화면서 모든 webview에 onPause와 onResume 시에 Timer를 설정해 주어야 한다.
             * 지정하지 않을 시 false로 설정된다.*/
            override fun isUsingWebviewTimer(): Boolean = false
        }
    }
}