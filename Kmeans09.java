

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.mahout.clustering.Cluster;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.Kluster;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * mahout版本0.9
 * 代码参考博客：http://blog.csdn.net/blognkliming/article/details/24153183
 * @author nwj
 * @description 实现的是kmeans算法，非分布式
 *
 */


public class Kmeans09 {

    public static final double[][] points = {
            {1, 1}, {2, 1}, {1, 2},
            {2, 2}, {3, 3}, {8, 8},
            {9, 8}, {8, 9}, {9, 9}};

    public static void writePointsToFile(List<Vector> points,
                                         String fileName,
                                         FileSystem fs,
                                         Configuration conf) throws IOException {
        Path path = new Path(fileName);
        SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf,
                path, LongWritable.class, VectorWritable.class);
        long recNum = 0;
        VectorWritable vec = new VectorWritable();
        for (Vector point : points) {
            vec.set(point);
            writer.append(new LongWritable(recNum++), vec);
        }
        writer.close();
    }

    public static List<Vector> getPoints(double[][] raw) {
        List<Vector> points = new ArrayList<Vector>();
        for (int i = 0; i < raw.length; i++) {
            double[] fr = raw[i];
            Vector vec = new RandomAccessSparseVector(fr.length);
            vec.assign(fr);
            points.add(vec);
        }
        return points;
    }

    public static void main(String args[]) throws Exception {
    	    	
        int k = 2;

        List<Vector> vectors = getPoints(points);

        File testData = new File("clustering/testdata");
        if (!testData.exists()) {
            testData.mkdir();
        }
        testData = new File("clustering/testdata/points");
        if (!testData.exists()) {
            testData.mkdir();
        }

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        writePointsToFile(vectors, "clustering/testdata/points/file1", fs, conf);

        Path path = new Path("clustering/testdata/clusters/part-00000");
        SequenceFile.Writer writer = new SequenceFile.Writer(fs, conf, path, Text.class, Kluster.class);
        
        
        for (int i = 0; i < k; i++) {
        	//此处初始化聚类的两个中心点
        	//把vectors的前两个点作为中心点
            Vector vec = vectors.get(i);
            //vector dot()方法是向量里的“点积”
            //EuclideanDistanceMeasure算的是距离是sqrt( (x1-x2)^2+(y1-y2)^2 )，而SquaredEuclideanDistanceMeasure是没开根号的，即两点距离(x1-x2)^2+(y1-y2)^2
            Kluster cluster = new Kluster(vec, i, new EuclideanDistanceMeasure());
            writer.append(new Text(cluster.getIdentifier()), cluster);
        }
        writer.close();

        KMeansDriver.run(conf,
                new Path("clustering/testdata/points"),
                new Path("clustering/testdata/clusters"),
                new Path("clustering/output"),
                0.001,
                10,
                true,
                0,
                true);

        SequenceFile.Reader reader = new SequenceFile.Reader(fs,
                new Path("clustering/output/" + Cluster.CLUSTERED_POINTS_DIR + "/part-m-0"), conf);

        IntWritable key = new IntWritable();
        WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();
        while (reader.next(key, value)) {
            System.out.println(value.toString() + " belongs to cluster " + key.toString());
        }
        reader.close();
    }

}
