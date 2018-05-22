# PNRS1_servisi

Treba obezbediti prelaz iz jednog u drugi aktiviti nakon pritiska na Button "1"/"2".
U pozadini trci Runnable, koji svake 2 sekunde upisuje random string koji se svaki put kada se pokrene Service pritiskom na dugme "START"
MainActivity-u, menja TextView u MainActivity.xml layout-u.


*************
Da bi ocitavao stalno, bez pritiska na START:
runThread();

gde je metoda:
    private void runThread() {

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Log.d(LOG_TAG, "Hello from Main" );
                                SharedPreferences mPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                String Text = mPreferences.getString("Text", null);
                                text.setText(Text);
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
