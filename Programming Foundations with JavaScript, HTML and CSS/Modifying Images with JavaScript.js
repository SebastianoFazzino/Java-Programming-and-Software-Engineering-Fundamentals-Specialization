//Write a JavaScript program that modifies an image by putting three vertical stripes on it - a red stripe on the left one third, 
//a green stripe in the middle, and a blue stripe on the right one third. 


var img = new SimpleImage("hilton.jpg");
print(img);

for (var pixel of img.values()){
   if (pixel.getX() <= img.getWidth()/3){
    pixel.setRed(255);
    }
    if (pixel.getX() >= img.getWidth()/3 && pixel.getX() <= img.getWidth()*2/3){
    pixel.setGreen(255);
    }
    else if (pixel.getX() > img.getWidth()*2 /3) {
        pixel.setBlue(255);
    }
}

print(img);







//Write a JavaScript function named swapRedGreen with one parameter pixel (representing a single pixel). This function should swap the red and green values of the pixel. 
//For example, if you have a pixel with red = 255, green = 100, blue = 150, after calling swapRedGreen on that pixel its new RGB values would be red = 100, green = 255, blue = 150.


var img = new SimpleImage('smallpanda.png');
print(img);

function swapRedGreen(img){
    for(var pixel of img.values()){
        var NewRed = pixel.getGreen();
        var NewGreen = pixel.getRed();
        pixel.setGreen(NewRed);
        pixel.setRed(NewGreen);

return img;
    }
}

var New = swapRedGreen(img);
print(New);





//Write code to change the Duke blue devil to be yellow.

var img = new SimpleImage("duke_blue_devil.png");
//we use getPixel to know the rgb values of the blue in the picture
print(img.getPixel(50,50));

for (var pixel of img.values()){
    if (pixel.getGreen() == 51){
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
}

print(img)
