package cloud.isaura.adso.files;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AdsoFileReader
{
    private final String path;
    private  SeekableByteChannel fc;

    private Long position;

    private Long size;

    private Long bufferSize;

    public AdsoFileReader(String path) throws IOException
    {
        this.path = path;
        this.fc = java.nio.file.Files.newByteChannel(Paths.get(path), StandardOpenOption.READ);
        this.position = 0L;
        this.size = this.fc.size();

   }

    public Boolean hasNext()
    {
        //System.out.println("position "+position);
        //System.out.println("size "+size);
        //System.out.println("buffersize "+bufferSize);
        return position < size;
    }

    public Long fileSize() throws IOException
    {
        return this.fc.size();
    }

    public void setBufferSize(Long bufferSize)
    {
        this.bufferSize = bufferSize;
    }

    public byte[] next() throws IOException
    {
        this.fc.position(this.position);
        Long allocateBuffer = position+bufferSize <= size ? bufferSize : size-position;
        ByteBuffer bf = ByteBuffer.allocate(allocateBuffer.intValue());
        this.fc.read(bf);
        bf.flip();
        bf.clear();
        this.position = this.position+this.bufferSize;
        return bf.array();
    }


}
