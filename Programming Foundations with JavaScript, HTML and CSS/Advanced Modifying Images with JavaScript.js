//Write the green screen algorithm you saw in the lecture video yourself. To make sure you really understand the code that was written in the video, 
//you should write the code yourself without looking at the video unless you get stuck and need to refer back to it for a hint.


var OldImg = new SimpleImage("drewRobert.png");
var BackgroundImg = new SimpleImage("dinos.png");
var NewImg = new SimpleImage(OldImg.getWidth(), OldImg.getHeight());
print(OldImg.getPixel(1,1));

for (var pixel of OldImg.values()){
    if (pixel.getGreen() > pixel.getRed() + pixel.getBlue()){
        var x = pixel.getX();
        var y = pixel.getY();
        var BackgroundPixel = BackgroundImg.getPixel(x,y);
        
        NewImg.setPixel(x,y,BackgroundPixel);
    }
    else{
        NewImg.setPixel(pixel.getX(),pixel.getY(),pixel);
    }
}

print(NewImg);



//Your friend is trying to write a program that draws a square 200 pixels by 200 pixels and that looks like this square with colors red (red value 255),
//green (green value 255), blue (blue value 255) and magenta (red value 255 and blue value 255). All other RGB values are set to 0.

//Here is their code:
//var img = new SimpleImage(200,200);
//for (var px of img.values()){
//  var x = px.getX();
//  var y = px.getY();
//  if (x < img.getWidth()/2){
//    px.setRed(255);
//  }
//  if (y>img.getHeight()/2){
//    px.setBlue(255);
//  }
//  else {
//    px.setGreen(255);
//  }
//}
//print (img);



var img = new SimpleImage(200,200);
for (var pixel of img.values()){
    var x = pixel.getX();
    var y = pixel.getY();
    if (x < img.getWidth()/2){
       pixel.setRed(255);
    }
    if (y > img.getHeight()/2){
       pixel.setBlue(255);
    }
    else if ((x > img.getWidth()/2) && (y < img.getHeight()/2)){
       pixel.setGreen(255);
    }
}

print(img);





