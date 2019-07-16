package com.academy.axway;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public interface LettersDistribution {

    void doLettersDistribution(String directoryToSearch) throws IOException;
}
