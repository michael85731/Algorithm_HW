import java.util.*;
import java.io.*;
import java.lang.Math;

class TestCase{
    private String caseType = "";
    private Net net = null;
    private int nodeAmount = 0;
    private List<Map<Integer, Integer>> testUnionPairs = new ArrayList<Map<Integer, Integer>>();

    private PrintWriter pw = null;
    private boolean writeFlag = false;
    private ArrayList<String> inputFilePath = new ArrayList<String>();
    private ArrayList<String>  outputFileName = new ArrayList<String>();
    private Timer timer = new Timer();

    public TestCase(String type){
        this.caseType = type;
        printHint();
        setFileOption();
    }

    private void printHint(){
        switch(this.caseType){
            case "Sample":
                System.out.println("File will be generate at current folder, pleace check it after program finish.");
                break;
            case "WorstCase":
                System.out.println("All test time will be list below, some might take few minute to run it.");
                break;
        }
    }

    private void setFileOption(){
        int sampleAmount   = 2;
        int worstAmount    = 13;
        String fatherPath  = "";
        String path        = "";
        File folder        = null;
        File[] listOfFiles = null;
        String outputName  = "";

        switch(this.caseType){
            case "Sample":
                for(int i = 0 ; i < 2 ; i++){
                    if(i == 0){
                        fatherPath = "test_data/quick union/Sample/";
                        outputName = "QuickUnion";
                    }else{
                        fatherPath = "test_data/weight quick union/Sample/";
                        outputName = "WeightedQuickUnion";
                    }
                    folder = new File(fatherPath);
                    listOfFiles = folder.listFiles(new FilenameFilter() {
                        public boolean accept(File dir, String name) {
                                return name.toLowerCase().contains("input");
                            }
                    });
                    for(int j = 0 ; j < listOfFiles.length ; j++){
                        this.inputFilePath.add(fatherPath + listOfFiles[j].getName());
                        this.outputFileName.add(outputName + "_case" + String.valueOf(j + 1) + "_output.txt");
                    }
                }
                this.writeFlag = true;
                break;
            case "WorstCase":
                for(int i = 0 ; i < 2 ; i++){
                    if(i == 0){
                        fatherPath = "test_data/quick union/Worst Case/";
                    }else{
                        fatherPath = "test_data/weight quick union/Worst Case/";
                    }
                    folder = new File(fatherPath);
                    listOfFiles = folder.listFiles();
                    for(int j = 0 ; j < listOfFiles.length ; j++){
                        this.inputFilePath.add(fatherPath + listOfFiles[j].getName());
                    }
                }
                this.outputFileName = new ArrayList<String>();
                this.writeFlag = false;
                break;
        }
    }

    public void process(){
        long executeTime = 0;
        for(int i = 0 ; i < this.inputFilePath.size() ; i++){
            this.initialOutput(i);
            this.readTestParameter(inputFilePath.get(i));
            this.setNet(i);
            this.writeResult(true);              // Print initital state(if now is in sample testing)
            executeTime = this.processUnion();
            this.printExecuteTime(executeTime);  // Only print it when testing worst case
        }
    }

    private void initialOutput(int counter){
        switch(this.caseType){
            case "Sample":
                try{
                    String fileName = outputFileName.get(counter);
                    File originOutput = new File(fileName);
                    originOutput.delete();
                    pw = new PrintWriter(fileName, "UTF-8");   // Prepare the file for sample test to write after erace exiting one.
                }catch(Exception e){
                    e.printStackTrace();
                    System.exit(1);
                }
                break;
            case "WorstCase":
                System.out.print(caseType + ": " + inputFilePath.get(counter) +" is ");
                break;
        }
    }

    private void readTestParameter(String path){
        try{
            FileReader fr              = new FileReader(path);
            BufferedReader br          = new BufferedReader(fr);
            String line                = "";
            Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
            int lineCounter            = 0;

            this.testUnionPairs = new ArrayList<Map<Integer, Integer>>();  // Reset current test pair set
            while(br.ready()){
                lineCounter++;
                line = br.readLine();

                if(lineCounter == 1){
                    nodeAmount = Integer.parseInt(line);
                }else if(lineCounter == 2){
                    continue;
                }else{
                    pair = extractPairFromString(line);
                    testUnionPairs.add(pair);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Map<Integer, Integer> extractPairFromString(String origin){
        String[] result = origin.split(" ");
        Map<Integer, Integer> pair = new HashMap<Integer, Integer>();
        pair.put(Integer.parseInt(result[0]), Integer.parseInt(result[1]));
        return pair;
    }

    private void setNet(int counter){
        if(counter < this.inputFilePath.size() / 2){
            this.net = new Net(nodeAmount, "QuickUnion");
        }else{
            this.net = new Net(nodeAmount, "WeightedQuickUnion");
        }
    }

    private long processUnion(){
        int secondNode = 0;
        boolean result = false;

        this.timer.start();
        for(int i = 0 ; i < testUnionPairs.size() ; i++){
            for(int firstNode : testUnionPairs.get(i).keySet()){
                secondNode = testUnionPairs.get(i).get(firstNode);
                result = net.union(firstNode, secondNode);              // If two node is not connected, union will return true(means union process)
                writeResult(result);
            }
        }
        return this.timer.stop();
    }

    private void writeResult(boolean result){
        if(this.caseType == "Sample"){
            if(result){
                net.writeToFile(pw);
            }else{
                pw.println("Connected.\n");
            }
            pw.flush();
        }
    }

    private void printExecuteTime(long time){
        if(caseType == "WorstCase"){
            System.out.println(time + " millisecond");
        }
    }
}
