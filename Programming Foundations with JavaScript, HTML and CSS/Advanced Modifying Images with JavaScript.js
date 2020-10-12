//Write the green screen algorithm you saw in the lecture video yourself. To make sure you really understand the code that was written in the video, 
you should write the code yourself without looking at the video unless you get stuck and need to refer back to it for a hint.


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
