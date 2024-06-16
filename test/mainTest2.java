import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
public class mainTest2 extends TestCase {




    private Main.DirectedGraph graph;

    public void setUp() throws Exception {
        // 初始化图对象
        graph = new Main.DirectedGraph();
        // 假设graph已经通过某种方式被填充了数据，这里只是示例
        // graph.generateGraph("path_to_input_file");
    }

    // 测试用例1：验证word1和word2之间没有桥接词
    @Test
    public void testBridgeWordsCase1() throws IOException {
        List<String> bridgeWords = graph.queryBridgeWords("d", "a");
        assertNull("Expected no bridge words between 'd' and 'a', but found some.", bridgeWords);
    }

    // 测试用例2：验证word1和word2之间没有桥接词
    @Test
    public void testBridgeWordsCase2() throws IOException {
        List<String> bridgeWords = graph.queryBridgeWords("a", "d");
        assertNull("Expected no bridge words between 'a' and 'd', but found some.", bridgeWords);
    }

    // 测试用例3：验证word1和word2之间没有桥接词
    @Test
    public void testBridgeWordsCase3() throws IOException {
        List<String> bridgeWords = graph.queryBridgeWords("a", "b");
        assertNull("Expected no bridge words between 'a' and 'b', but found some.", bridgeWords);
    }

    // 测试用例4：验证word1和word2之间存在桥接词word3
    @Test
    public void testBridgeWordsCase4() throws IOException {
        List<String> bridgeWords = graph.queryBridgeWords("a", "c");
          assertNull("Expected bridge words between 'a' and 'c', but found none.", bridgeWords);
         assertTrue("Expected 'b' to be a bridge word between 'a' and 'c', but it was not found.",
            bridgeWords.contains("b"));
    }

    @After
    public void tearDown() throws Exception {
        // 清理工作，如果需要的话
        graph = null;
    }
}
