package de.hfu;
import org.junit.Assert;
import org.junit.Test;
public class UtilTest {

	@Test
	public void halbjahrTest() {
		//True
		Assert.assertTrue(Util.istErstesHalbjahr(1));
		Assert.assertTrue(Util.istErstesHalbjahr(2));
		Assert.assertTrue(Util.istErstesHalbjahr(3));
		Assert.assertTrue(Util.istErstesHalbjahr(4));
		Assert.assertTrue(Util.istErstesHalbjahr(5));
		Assert.assertTrue(Util.istErstesHalbjahr(6));
		
		
		//False
		Assert.assertFalse(Util.istErstesHalbjahr(7));
		Assert.assertFalse(Util.istErstesHalbjahr(8));
		Assert.assertFalse(Util.istErstesHalbjahr(9));
		Assert.assertFalse(Util.istErstesHalbjahr(10));
		Assert.assertFalse(Util.istErstesHalbjahr(11));
		Assert.assertFalse(Util.istErstesHalbjahr(12));
	}
}
