import arduino.Arduino;

public class ComReader implements Runnable{
    Arduino arduino;

    public ComReader() {
        int portNumber=3;
        boolean connected = false;
        while (!connected) {
            try{
                this.arduino = new Arduino("COM"+portNumber , 9600);
                connected = arduino.openConnection();
                if (!connected){
                    System.out.println("COM-port #" +portNumber+" doesn't exist");
                    portNumber++;
                    if (portNumber>50) portNumber=0;
                }
            } catch (NullPointerException ex){

            }

        }
        System.out.println("Соединение установлено: " + connected);
    }

    @Override
    public void run() {
        while (true) {
            String uidRead;

            uidRead = arduino.serialRead();
            if (!AppMain.uid.equals(uidRead)) {
                AppMain.uid = uidRead;
                System.out.println(uidRead);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
