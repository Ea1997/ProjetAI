public class carte
{
    public static final int NUM_ROWS=10;
    public static final int NUM_COLUMNS=10;
    public static final int NUM_PITS=10;

    private ressource[][]grid;
    private int ladderC;
    private int ladderR;
    private int x=10;
    private int a;
    private int s=0;
    private int ss=0;
    Agent wp = new Agent();

    public int getS() {
        return s;
    }

    public int getSs() {
        return ss;
    }

    private int b;
    public carte()
    {
        createMap();
    }
    public void createMap()
    {
        grid  = new ressource[10][10];

        for(int d=0;d<10;d++)
        {
            for(int dd=0;dd<10;dd++)
            {
                grid[d][dd] = new ressource();
            }
        }
        while(x>0)
        {

            a = (int)(Math.random()*10);
            b = (int)(Math.random()*10);

            if(grid[a][b].toString().equals("*") && a != 9 && b != 0)
            {
                grid[a][b].setPit(true);
                x--;
                if(a+1<=9)
                {
                    grid[a + 1][b].setBreeze(true);


                }
                if(a-1>=0)
                {
                    grid[a - 1][b].setBreeze(true);


                }
                if(b+1<=9)
                {
                    grid[a][b + 1].setBreeze(true);


                }
                if(b-1>=0)
                {
                    grid[a][b - 1].setBreeze(true);

                }
            }
        }
        x=1;
        while(x>0)
        {
            a = (int)(Math.random()*10);
            b = (int)(Math.random()*10);

            if(grid[a][b].toString().equals("*") && a != 9 && b != 0)
            {

                grid[a][b].setGold(true);
                x--;

            }
        }
        x=1;
        while(x>0)
        {
            a = (int)(Math.random()*10);
            b = (int)(Math.random()*10);

            if(grid[a][b].toString().equals("*") && a != 9 && b != 0)
            {
                grid[a][b].setWumpus(true);
                x--;
                if(a+1<=9)
                    grid[a + 1][b].setStench(true);
                if(a-1>=0)
                    grid[a-1][b].setStench(true);
                if(b+1<=9)
                    grid[a][b+1].setStench(true);
                if(b-1>=0)
                    grid[a][b-1].setStench(true);



            }
        }
        x=1;
        while(x>0)
        {
            a = 9;
            b = 0;

            if(grid[a][b].toString().equals("*") )
            {
                grid[a][b].setLadder(true);
                ladderR=a;
                ladderC=b;
                x--;
            }
        }
        x=10;
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if(grid[x][y].isPit() && grid[x][y].isBreeze())
                {
                    grid[x][y].setBreeze(false);
                }
                if(grid[x][y].isPit() && grid[x][y].isStench())
                {
                    grid[x][y].setStench(false);
                }
            }
        }
    }
    public int getLadderC() {
        return ladderC;
    }

    public int getLadderR() {
        return ladderR;
    }

    public ressource[][] getGrid() {
        return grid;
    }

    public ressource getSquare(int col, int row)
    {
        if(col <=9 && row<=9 && col>=0 && row>=0)
        {
            return grid[row][col];
        }
        return null;
    }
    @Override
    public String toString()
    {
        String t = "";
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                System.out.print(grid[y][x]);
            }
            System.out.print("\n");
        }
        return t;
    }
}