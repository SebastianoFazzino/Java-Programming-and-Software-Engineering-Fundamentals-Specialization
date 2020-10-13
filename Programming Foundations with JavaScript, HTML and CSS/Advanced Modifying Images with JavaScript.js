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





//Write a function named setBlack that has one parameter pixel (representing a single pixel) and returns pixel
//with its red, green, and blue components changed so that the pixel’s color is black.

//Now you will write another function named addBorder. This function will add a black border to an image, such
//as in the following example:

//On the left, we have the original image, and on the right, we have modified the image by giving it a black border
//that is 10 pixels thick. Note that the image size of the image with the border is the same as the original image
//because the border is not added around the outside of the original image, instead it covers up some of the
//original image.

//Work through the seven steps to write this function. Work an example by hand and note the steps you took before
//translating your algorithm to code. Which pixels should be part of the border? How will you identify those pixel?
//Once you have identified them, how will you make them black?


var img = new SimpleImage("smallpanda.png");
print(img);

function setBlack(pixel){
    pixel.setRed(0);
    pixel.setGreen(0);
    pixel.setBlue(0);
    return pixel
}


function addBorder(image, thickness){
    var h = image.getHeight();
    var w = image.getWidth();
    for (var px of image.values()){
        var x = px.getX();
        var y = px.getY();
        if (x <= thickness || y <= thickness){
            setBlack(px);
        }
        if (x >= w - thickness || y >= h - thickness){
            setBlack(px);
        }
    }
    return image;
}


var picture = addBorder(img,10);
print(picture);


