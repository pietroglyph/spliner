package org.usfirst.frc.team4915.robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    private Robot m_robot;
    private Logger m_logger;
    private Set<String> m_autoReplayOptions = new HashSet<>();
    
    public OI(Robot robot) {
        m_robot = robot;
        m_logger = new Logger("OI", Logger.Level.DEBUG);
    }
    
    private void initAutoOI()
    {   
        // here we enumerate all recordings
        Path root = Paths.get(System.getProperty("user.home"), "SplinerRecordings");
        if (!Files.isDirectory(root))
        {
            try
            {
                Files.createDirectories(root);
            }
            catch (IOException e)
            {
                m_logger.exception(e, true);
            }
        }
        if (!Files.isWritable(root))
        {
            m_logger.error("Recordings folder isn't writable!");
            return;
        }
        try
        {
            // since we don't know the alliance here, we have a problem.
            // for now, the workaround is to populate all presets.
            // String alliance = getAlliance();
            // String other = alliance.equals("Blue") ? "Red" : "Blue";
            Files.list(root)
                    .filter(Files::isReadable)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    // Hide replays meant for the other alliance
                    // .filter(filename -> !filename.contains(other))
                    .forEach(file ->
                    {
                        m_logger.debug("Autonomous option found: " + file);
                    });
        }
        catch (IOException e)
        {
            m_logger.exception(e, true);
        }

        SmartDashboard.putString("AutoStrategyOptions", String.join(",", m_autoReplayOptions));
    }
    
    public Command getAutoCommand() {
        String strategy = SmartDashboard.getString("AutoStrategy", "");
        m_logger.debug("Searching for " + strategy);
        if (m_autoReplayOptions.contains(strategy))
        {
            m_logger.notice("Found a replay named " + strategy);
            return new Command; // TODO: Replay command/another way to do this
        }
        m_logger.error("Didn't find " + strategy);
        return null;
    }
    
    
}
