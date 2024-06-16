import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Map;

public class MainTest {

    private Main.DirectedGraph graph;

    @Before
    public void setUp() throws Exception {
        // 初始化图对象
        graph = new Main.DirectedGraph();
    }

    @After
    public void tearDown() throws Exception {
        // 清理工作，如果需要的话
        graph = null;
    }

    // 辅助方法，用于验证从from到to的边是否存在且权重为expectedWeight
    private void assertEdgeExists(String from, String to, int expectedWeight) {
        Map<String, Integer> adjacencyMap = graph.adjacencyList.get(from);
        assertTrue("Edge from '" + from + "' to '" + to + "' with weight " + expectedWeight + " does not exist.",
                adjacencyMap != null && adjacencyMap.containsKey(to) && adjacencyMap.get(to).equals(expectedWeight));
    }

    // 测试用例1：纯小写字母文本
    @Test
    public void testLowerCaseText() throws IOException {
        graph.generateGraph("src/11.txt");
        assertEdgeExists("hello", "world", 1);
    }

    // 测试用例2：包含英文标点的文本
    @Test
    public void testTextWithPunctuation() throws IOException {
        graph.generateGraph("src/12.txt");
        assertEdgeExists("hello", "world", 1);
        assertEdgeExists("world", "how", 1);
        assertEdgeExists("how", "are", 1);
        assertEdgeExists("are", "you", 1);
    }

    // 测试用例3：包含换行/回车符的文本
    @Test
    public void testTextWithNewLines() throws IOException {
        String input = "src/13.txt";
        graph.generateGraph(input);
        assertEdgeExists("hello", "world", 1);
        assertEdgeExists("world", "how", 1);
        assertEdgeExists("how", "are", 1);
        assertEdgeExists("are", "you", 1);
    }

    // 测试用例4：大小写不敏感
    @Test
    public void testCaseInsensitivity() throws IOException {
        graph.generateGraph("src/14.txt");
        assertEdgeExists("hello", "world", 1);
        assertEdgeExists("world", "hello", 1);
    }

    // 测试用例5：包含特殊符号的文本
    @Test
    public void testTextWithSpecialSymbols() throws IOException {
        graph.generateGraph("src/15.txt");
        assertEdgeExists("hello", "world", 1);
    }

    // 测试用例6：包含数字的文本
    @Test
    public void testTextWithNumbers() throws IOException {
        graph.generateGraph("src/16.txt");
        assertEdgeExists("hello", "world", 1);
    }

    // 测试用例7：其他语言文本
    @Test(expected = IOException.class) // 期望抛出IOException或其他自定义异常
    public void testNonEnglishText() throws IOException {
        graph.generateGraph("src/17.txt");
        // 这里不需要断言，因为测试期望的是抛出异常
    }
}