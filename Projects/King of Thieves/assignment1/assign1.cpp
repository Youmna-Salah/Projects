//
//  assign1.cpp
//  assignment1
//
//  Created by youmna salah on 10/17/15.
//  Copyright © 2015 youmna salah. All rights reserved.
//

//
//  Assignment1.cpp
//  Try
//
//  Created by youmna salah on 10/13/15.
//  Copyright © 2015 youmna salah. All rights reserved.
//

#include <stdio.h>
#include <math.h>
#include <random>
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>
#include <iostream>                      // Header File For Standard Input/Output    ( ADD )
#include <stdarg.h>


int starttime = 0;
int tformula = 0;
int jumpinglevel =0;
float ycolosion = 0;
//float Gravity =9.98;
//float intitialVelocity = 14.11;
float velocityY = 20;
float velocityX = 1.00;
float cx1 =velocityY*sin(45),cy1 =velocityY*cos(45) ,cy2 = 0.5*9.98;
int currentlevel = 0;
bool won = 0,lost=0,freez =0;
float playerx =40.0f;
float playery =20.0f;
float step = 1.0f;
int t = 1;
bool jumping = 0;
float directionx = 1,directiony = 1;
void drawRect(int x, int y, int w, int h);
void drawCircle(int x, int y, float r);
void Key(unsigned char key, int x, int y);
void KeyUp(unsigned char key, int x, int y);
//void Mouse(int button, int state, int x, int y);
void Timer(int value);
void Display();

void Timer(int value) {
    
    if(playerx>=200&&playerx<=270){
        if(playery<=60){
            lost = 1;
        }
        else
            lost = 0;
    }
    if(playery>=360 && playerx<=90){
        playery=360;
        playerx=20;
        won = 1;
        //game is over you won
    }
    if((playerx>=780 || playerx<= 40)){
        ycolosion = playery;
    }
    if(!(won || lost))
        t++;
    tformula++;
    glutPostRedisplay();
    glutTimerFunc(1* 1000, Timer, 0);
}


void displayText( float x, float y, int r, int g, int b, std::string string ) {
    long j =  string.length();
    
    glColor3f( r, g, b );
    glRasterPos2f( x, y );
    for( int i = 0; i < j; i++ ) {
        glutBitmapCharacter( GLUT_BITMAP_HELVETICA_18, string[i] );
    }
}

void anim(){
    
    //
    //    if(playerx>=200&&playerx<=270){
    //        if(playery<=60){
    //            lost = 1;
    //        }
    //        else
    //            lost = 0;
    //    }
    //    if(playery==360 && playerx<=40){
    //        playery=360;
    //        playerx=20;
    //        won = 1;
    //        //game is over you won
    //    }
    if(jumping){
        if(playery>=20&&playery<=280){
            playery += ((cy1*tformula*directiony-cy2*pow(tformula,2))+50);
            playerx += directionx*cx1*t/8;
            if((playerx>=780 || playerx<= 40)){
                velocityX*=-1;
                directionx*=-1;
                velocityY+=2;
                //                        directiony*=-1;
                tformula = 0;
                if(playerx>=780)
                    playerx = 780;
                else
                    playerx = 40;
            }
        }
        
        else{
            //maximum is reached & descending
            jumping = 0;
            directiony*=-1;
            while(playery>20){
                playery += ((cy1*tformula*directiony-cy2*pow(tformula,2))+50);
                playerx += directionx*cx1*t/8;
                if((playerx>=780 || playerx<= 40)){
                    velocityX*=-1;
                    velocityY+=2;
                    if(playerx>=780)
                        playerx = 780;
                    else
                        playerx = 40;
                    directionx*=-1;
                    //                            directiony*=-1;
                    tformula = 0;
                }
            }
        }
    }
    if(playery>=360 && playerx>=320){
        
        //        playery=20;
        currentlevel = 0;
        while(playery>20){
            playery-=2;
            playerx+=velocityX;
            if(playerx>=780){
                directionx*=-1;
                velocityX*=-1;
            }
        }
        //        jumping1=0;
        //        jumping2=0;
        //        for(;playerx<799;playerx+=Gravity*2);
        //        for(;playery>40;playery-=Gravity*2);
    }
    
    
    if(won){
        displayText(400, 600, 0, 1,0, "CHARGES DROPED :D \n YOU ARE FREE TO GO!!!");
        playerx= 20;
        playery =360;
        //                sleep(1);
    }
    if(lost){
        displayText(400, 600, 1,0, 0, "JURIES FOUND THAT YOU ARE GUILTY :/ !!");
        t = 0;
        playerx=20.0f;
        playery=20.0f;
        glutPostRedisplay();
        
        //lost = 0;
    }
    //    }
    playerx+=velocityX;
    if((playerx>=780 || playerx<= 40)){
        velocityX*=-1;
        directionx*=-1;
    }
    
    glutPostRedisplay();
}

int main(int argc, char** argr) {
    glutInit(&argc, argr);
    glutInitWindowSize(800, 800);
    glutInitWindowPosition(150, 150);
    glutCreateWindow("King of theives");
    glutDisplayFunc(Display);
    glutIdleFunc(anim);
    glutKeyboardFunc(Key);
    glutKeyboardUpFunc(KeyUp);  // sets the KeyboardUp handler function; called when a key is released
    glutTimerFunc(0, Timer, 0); // sets the Timer handler function; which runs every `Threshold` milliseconds (1st argument)
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glClearColor(1, 1, 1, 0);
    gluOrtho2D(0, 800, 0, 800);
    
    glutMainLoop();
    return 0;
}

void drawRect(int x, int y, int w, int h) {
    glBegin(GL_POLYGON);
    glVertex2f(x, y);
    glVertex2f(x + w, y);
    glVertex2f(x + w, y + h);
    glVertex2f(x, y + h);
    glEnd();
}
// draws a circle using OpenGL's gluDisk, given (x,y) of its center and tis radius
void drawCircle(int x, int y, float r) {
    glPushMatrix();
    glTranslatef(x, y, 0);
    GLUquadric *quadObj = gluNewQuadric();
    gluDisk(quadObj, 0, r, 50, 50);
    glPopMatrix();
}

void Key(unsigned char key, int x, int y) {
    if(key==' '){
        jumping =1 ;
        //        starttime = t;
        //        tformula = t-starttime;
        //
        //        if(playery>=20&&playery<=280){
        //            playery += ((cy1*tformula*directiony-cy2*pow(tformula,2))+50);
        //            playerx += directionx*cx1*t/8;
        //            if((playerx>=780 || playerx<= 40)){
        //                velocityX*=-1;
        //                directionx*=-1;
        //                directiony*=-1;
        //                tformula = 0;
        //            }
        //        }
        //
        //        else{
        //            //maximum is reached & descending
        //            jumping = 0;
        //              directiony*=-1;
        //            while(playery>20){
        //                playery += ((cy1*tformula*directiony-cy2*pow(tformula,2))+50);
        //                playerx += directionx*cx1*t/8;
        //                if((playerx>=780 || playerx<= 40)){
        //                    velocityX*=-1;
        //                    directionx*=-1;
        //                    directiony*=-1;
        //                    tformula = 0;
        //                }
        //            }
        //        }
        
    }
    glutPostRedisplay();
    
}
void KeyUp(unsigned char key, int x, int y) {
    if(key == ' ')
    {
        jumping = 0;
        tformula = 0;
        velocityY-=2;
        //        if(playery<360){
        while(playery>20){
            playery-=10;
            playerx+=velocityX;
        }
        playery = 20;
        //        }
        //        else{
        //            while(playery>360){
        //                playery-=10;
        //                playerx+=velocityX;
        //            }
        //            playery=360;
        //        }
        
    }
    if(won){
        playerx= 20;
        playery =440;
        //        glutPostRedisplay();
    }
    if(lost){
        playerx = 40;
        playery = 20;
    }
    glutPostRedisplay();
}
void Display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glColor3f(0, 0, 1);
    drawCircle(playerx,playery, 20);
    glPushMatrix();
    glColor3f(0, 0, 0);
    drawRect(0,320 ,300, 20);
    glColor3f(1, 0, 1);
    drawRect(0,340, 20, 200);
    glColor3f(1, 0, 1);
    drawRect(200,0, 50, 20);
    glPopMatrix();
    
    //    if(freez){
    //        playerx =700;
    //        playery =600;
    //        freez = 0;
    //    }
    std::string name="TIME : ",result;
    char numstr[2];
    sprintf(numstr, "%i",t);
    result = name + numstr;
    displayText(600, 700, 0, 0, 0, result);
    if(won){
        displayText(400, 600, 0, 1,0, " CHARGES DROPED :D YOU ARE FREE TO GO!!!");
    }
    if(lost){
        displayText(400, 600, 1, 0,0, "JURIES FOUND THAT YOU ARE GUILTY :/ !!");
        //        usleep(1500000);
        
        t = 0;
        
    }
    
    
    glFlush();
    
}
