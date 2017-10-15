import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { LoginPage } from '../pages/login/login';
import { JoinWithEmailPage } from '../pages/joinWithEmail/joinWithEmail';
import { LoginWithEmailPage } from '../pages/loginWithEmail/loginWithEmail';
import { FindEmailPasswordPage } from '../pages/findEmailPassword/findEmailPassword';
import { HomePage } from '../pages/home/home';
import { TalkPage } from '../pages/talk/talk';

@NgModule({
  declarations: [
    MyApp,
    LoginPage,
    JoinWithEmailPage,
    LoginWithEmailPage,
    FindEmailPasswordPage,
    HomePage,
    TalkPage
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginPage,
    JoinWithEmailPage,
    LoginWithEmailPage,
    FindEmailPasswordPage,
    HomePage,
    TalkPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
