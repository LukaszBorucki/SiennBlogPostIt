package co.borucki.SiennBlogPostIt.entrypoint.blogpost;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BlogHeaderDTOTest {


    @Test
    public void givenLongThan30SignsContents_whenSetContents_than30SignsContentShouldBeSet() {
        BlogHeaderDTO blogHeaderDTO = new BlogHeaderDTO(1, "", "");
        String givenContents = "123456789012345678901234567890 - last 0 is 30'ty characters";
        String expected = "123456789012345678901234567890";
        blogHeaderDTO.setContents(givenContents);
        String actual = blogHeaderDTO.getContents();
        assertEquals(expected, actual);
    }

    @Test
    public void givenLessThan30SignsContents_whenSetContents_thanGivenContentsShouldBeSet() {
        BlogHeaderDTO blogHeaderDTO = new BlogHeaderDTO(1, "", "");
        String givenContents = "short than 30 characters";
        blogHeaderDTO.setContents(givenContents);
        String actual = blogHeaderDTO.getContents();
        assertEquals(givenContents, actual);
    }
}