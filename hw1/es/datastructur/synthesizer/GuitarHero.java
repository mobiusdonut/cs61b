/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
//import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] guitarStringObjects = new GuitarString[37];
        GuitarString stringX;
        GuitarString stringToPlay;

        for (int i = 0; i < 37; i++)
        {
            double freq = 440.0 * Math.pow(2.0, (i - 24.0) / 12.0);
            stringX = new GuitarString(freq);
            guitarStringObjects[i] = stringX;
            System.out.print(keyboard.charAt(i) + " ");
            System.out.println(freq);
        }

        while (true) {

          /* check if the user has typed a key; if so, get the index,
          then find the corresponding guitarstring at that index in the guitar objects */
          if (StdDraw.hasNextKeyTyped())
          {
              char inputKey = StdDraw.nextKeyTyped();
              int indexOfInputKey = keyboard.indexOf(inputKey);
              if (indexOfInputKey == -1) {
                continue;
              }

              stringToPlay = guitarStringObjects[indexOfInputKey];
              stringToPlay.pluck();
          }

          // compute the superposition of samples
          double sample = 0.0;
          for (GuitarString x : guitarStringObjects)
          {
              sample += x.sample();
          }

          // play the sample on standard audio
          StdAudio.play(sample);

          // advance the simulation of each guitar string by one step
          for (GuitarString x: guitarStringObjects)
          {
              x.tic();
          }
        }
    }
}
