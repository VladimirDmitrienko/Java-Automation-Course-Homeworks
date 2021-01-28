package parser;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XMLParserTest {

    @Test
    public void testAttributeWithoutClosingQuote() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\attributeWithoutClosingQuote.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testEmptyFile() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\emptyFile.xml");
        try {
            parser.parseDocument();
        }
        catch (Exception e) {
            Assert.fail();
        }
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testEmptyTag() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\emptyTag.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testEmptyTagContent() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\emptyTagContent.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testMissingBracket() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\missingBracket.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testMissingClosingTag() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\missingClosingTag.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testMissingRootTag() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\missingRootTag.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testNotClosedComment() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\notClosedComment.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testNotXML() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\notXMl.txt");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testSelfClosingTag() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\selfClosingTag.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithAmpersand() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithAmpersand.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithApostrophe() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithApostrophe.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithAttribute() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithAttribute.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithGreaterSign() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithGreaterSign.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithInvalidAmpersand() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithInvalidAmpersand.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithInvalidLessSign() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithInvalidLessSign.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithLessSign() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithLessSign.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithQuote() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithQuote.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testValidXML() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\valid.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testWithComment() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\withComment.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testWithProlog() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\withProlog.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testInvalidClosingTagsSequence() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\invalidClosingTagsSequence.xml");
        parser.parseDocument();
        Assert.assertNotEquals(parser.getErrors().size(), 0);
    }

    @Test
    public void testTagWithCDATA() {
        XMLParser parser = new XMLParser("src\\test\\java\\resources\\tagWithCDATA.xml");
        parser.parseDocument();
        Assert.assertEquals(parser.getErrors().size(), 0);
    }
}